package com.example.flowcoroutine

import kotlinx.coroutines.*

/*
7. Giải thích cơ chế hủy và xử lý lỗi trong coroutine
    - Không có hàm kiểm tra trạng thái thì không hủy được couroutine( hàm delay(), isActive, ensureActive())
    -
*/


fun main() = runBlocking {
    val deferred1 = async { "Kết quả 1" }
    val deferred2 = async { throw Exception("Lỗi trong deferred 2") }

    val results = mutableListOf<String>()

    try {
        results.add(deferred1.await())
    } catch (e: Exception) {
        println("Lỗi trong deferred 1: ${e.message}")
    }

    try {
        results.add(deferred2.await())
    } catch (e: Exception) {
        println("Lỗi trong deferred 2: ${e.message}")
    }

    println("Kết quả đã thu thập: $results")
}
