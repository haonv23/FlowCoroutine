package com.example.flowcoroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*1. Coroutine là gì và nó giải quyết vấn đề gì trong lập trình Android?
    Coroutine là một cơ chế lập trình bất đồng bộ được Kotlin cung cấp để giúp
    quản lý luồng (thread) một cách tối ưu. Nó cho phép thực thi tiết kiệm tài nguyên hệ thống.
    - Tránh chặn UI Thread
    - Tiêu tốn ít bộ nhớ hơn Thread
    - Dễ dàng hủy để tránh rò rỉ bộ nhớ
 */

fun main (){
    runBlocking {
        val startTime = System.currentTimeMillis()

        val jobs = List(100000) {
            launch {
                delay(1000)
                println("Coroutine $it done")
            }
        }

        jobs.forEach { it.join() }
        val endTime = System.currentTimeMillis()
        println("Total time: ${endTime - startTime} ms")
    }

//    val startTime = System.currentTimeMillis()
//    val threads = List(100000) {
//        Thread {
//            Thread.sleep(1000)
//            println("Thread $it done")
//        }.apply { start() }
//    }
//
//    threads.forEach { it.join() }
//
//    val endTime = System.currentTimeMillis()
//    println("Total time: ${endTime - startTime} ms")
}

