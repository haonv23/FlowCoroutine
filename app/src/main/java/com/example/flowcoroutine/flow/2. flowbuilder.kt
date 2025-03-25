package com.example.flowcoroutine.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
2. Các builder phổ biến để tạo Flow là gì?
*/

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
            listOf(10,12,14).asFlow().collect { value ->
                delay(1000)
                println("Received value from asFlow: $value")
            }
        }
        launch {
            flowOf(11,13,15).collect { value ->
                delay(1000)
                println("Received value from flowOf: $value")
            }
        }
    }
}
