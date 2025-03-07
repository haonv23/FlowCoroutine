package com.example.flowcoroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
6. Flow là gì và làm thế nào để sử dụng nó với coroutine?
    - Là một API được thiết kế để xử lý luồng dữ liệu bất đồng bộ trong Kotlin Coroutines
    phát ra nhiều giá trị tuần tự theo thời gian
    - Chỉ phát dữ liệu khi có collector thu thập
    - Flow hoạt động trên Coroutine, không block luồng chính.
*/

fun main(){
    runBlocking {
        val numbersFlow: Flow<Int> = flow {
            for (i in 1..5) {
                delay(500)
                emit(i)
            }
        }.flowOn(Dispatchers.IO)

        launch {
            numbersFlow.collect { value ->
                println("Received: $value")
            }
        }
        launch {
            numbersFlow.collect { value ->
                println("Received2: $value")
            }
        }
    }
}