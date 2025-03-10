package com.example.flowcoroutine.couroutine

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
4. Giải thích sự khác biệt giữa launch, async và runBlocking.
    - launch:
        + Trả về một job
        + Chạy trên scope cha, không chặn thread
        + Xử lý ngoại lệ: Truyền lên CoroutineScope
    - async:
        + Trả về một Deferred
        + Chạy trên scope cha, không chặn thread
        + Xử lý ngoại lệ: Khi gọi await(), không khi khởi tạo
    - runBlocking:
        + Trả về Unit
        + Tạo một scope mới và chặn thread hiện tại
        + Ngay lập tức, như code đồng bộ bình thường
*/

fun main (){
    runBlocking {
        val job = launch {
            delay(1000)
            println("Coroutine launch done")
        }
        runBlocking {
            println("Start")
            delay(5000)
            println("End")
        }
        val deferred = async {
            delay(1000)
            "Coroutine launch done1"
        }
        println(deferred.await())
    }
}