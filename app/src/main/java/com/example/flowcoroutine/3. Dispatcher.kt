package com.example.flowcoroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//3. Dispatcher là gì và các loại Dispatcher phổ biến?
//    - Dispatcher xác định thread mà coroutine sẽ chạy trên đó
//    - Các loại Dispatcher phổ biến:
//        + Dispatchers.Main
//        + Dispatchers.IO
//        + Dispatchers.Default

@OptIn(ExperimentalStdlibApi::class)
fun main(){
    runBlocking {
        launch(Dispatchers.IO) {
            println("Current Dispatcher: ${coroutineContext[CoroutineDispatcher]}")
        }
        launch(Dispatchers.Default) {
            println("Current Dispatcher: ${coroutineContext[CoroutineDispatcher]}")
        }
    }
}
