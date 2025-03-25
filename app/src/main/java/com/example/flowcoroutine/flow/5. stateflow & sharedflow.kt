package com.example.flowcoroutine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
    5. StateFlow và SharedFlow là gì và sự khác biệt giữa chúng?
        - Cả hai đều là luồng nóng, lưu giữ giá trị mới nhất hiện tại, sharedflow có thể đặt replay > 0 để lưu các giá trị trước đó
        - stateflow bắt buộc phải có giá trị khởi tạo
        -
*/

fun main(): Unit = runBlocking{
    val myFlow = flow {
        emit("Hello, World!")
        delay(1000)
        emit("Hello, World!2")
        delay(1000)
        emit("Hello, World!3")
    }.shareIn(
        scope = this,
        started = SharingStarted.WhileSubscribed(5000),
        replay = 3
    )

    launch {
        myFlow.collect{ values->
            println("Received value1: $values")
        }
    }
    launch {
        delay(1000)
        myFlow.collect{ values->
            println("Received value2: $values")
        }
    }

//    val myFlow2 = flow {
//        emit("Hello, World!")
//        delay(1000)
//        emit("Hello, World!6")
//        delay(1000)
//        emit("Hello, World!6")
//    }.stateIn(
//        scope = this,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = ""
//    )
//
//    myFlow2.collect{ values->
//        println("Received value: $values")
//    }

}
