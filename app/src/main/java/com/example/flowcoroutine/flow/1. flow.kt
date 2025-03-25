package com.example.flowcoroutine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*1. Flow là gì và nó khác gì so với các hàm suspend thông thường?
    - Flow là một API trong Kotlin Coroutines dùng để xử lý luồng dữ liệu bất đồng bộ (asynchronous stream).Nó giúp phát và thu thập dữ liệu theo thời gian
    - Flow có thể phát ra giá trị liên tục, suspend fun chỉ trả về 1 lần rồi kết thúc
* */



fun main(): Unit = runBlocking{
    launch {
        launch {
            flow{
                repeat(10) {
                    delay(1000)
                    emit(it)
                }
            }.collect { value ->
                println("Received value: $value")
            }
        }
        launch {
            funSuspend()
        }
    }

}

suspend fun funSuspend(){
    delay(1000)
    println("Inside suspend function")
}