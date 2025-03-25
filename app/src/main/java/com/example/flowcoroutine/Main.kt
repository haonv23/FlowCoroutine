package com.example.flowcoroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

/*




6. Flow exception handling: Các cơ chế xử lý lỗi trong Flow?*/

@OptIn(ExperimentalStdlibApi::class)
fun main (){
    suspend fun logCoroutineScope() {
        delay(1000)
        println("Job: ${coroutineContext[Job]}")
        println("Dispatcher: ${coroutineContext[CoroutineDispatcher]}")
        println("Name: ${coroutineContext[CoroutineName]?.name}")
        println("Active: ${coroutineContext.isActive}")
    }

    runBlocking {
        val job1 = launch(Dispatchers.IO) {
            logCoroutineScope()
        }
        job1.join()
        val job2 = launch(Dispatchers.Default) {
            logCoroutineScope()
        }
        val result = async {
            delay(1000)
            "asd!"
        }
        println(result.await())
    }
}