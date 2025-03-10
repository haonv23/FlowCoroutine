package com.example.flowcoroutine.couroutine

import kotlinx.coroutines.*

/*
7. Giải thích cơ chế hủy và xử lý lỗi trong coroutine
    - Không có hàm kiểm tra trạng thái thì không hủy được couroutine( hàm delay(), isActive, ensureActive())
    - launch tự động lan truyền exception ra ngoài scope, async chỉ lan truyền ra ngoài scope khi gọi await
    - khi scope có ngoại lệ, các coroutine khác cũng bị dừng
*/


//fun main() = runBlocking {
//    val handler = CoroutineExceptionHandler { _, exception ->
//        println("CoroutineExceptionHandler đã bắt được exception: ${exception.message}")
//    }
//    val coroutineScope = CoroutineScope(Dispatchers.Default + handler)
//
//    val job = coroutineScope.launch {
//        throw NullPointerException()
//    }
//    // exception sẽ được bắt bởi CoroutineExceptionHandler khi launch
//    job.join()
//
//    val deferred = coroutineScope.async {
//        throw IndexOutOfBoundsException()
//    }
//    // exception không được lan ra ngoài
//    try {
//        deferred.await()
//    } catch (e: Exception) {
//        println("Deferred đã bắt được exception: ${e.message}")
//    }
//    // exception sẽ được bắt bởi try catch khi await
//}


//fun main() {
//    runBlocking {
//        val handler = CoroutineExceptionHandler { _, exception ->
//            println("CoroutineExceptionHandler got $exception")
//        }
//        val job = GlobalScope.launch(handler) {
//            launch { // the first child
//                try {
//                    delay(Long.MAX_VALUE)
//                } finally {
//                    println("Children are cancelled, but exception is not handled until all children terminate") // vẫn in ra
//                    delay(100)
//                    println("The first child finished its non cancellable block")//không in ra vì job đã bị huỷ
//                }
//            }
//            launch { // the second child
//                delay(10)
//                println("Second child throws an exception")
//                throw ArithmeticException() // ngoại lệ xảy ra, các couroutine bị huỷ
//            }
//        }
//        job.join()
//    }
//}

