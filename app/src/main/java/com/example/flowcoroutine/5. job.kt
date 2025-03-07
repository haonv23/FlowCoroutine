package com.example.flowcoroutine.ui.theme

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
5. Job là gì? Làm thế nào để quản lý vòng đời của coroutine?
    - Đại diện cho một công việc có thể hủy bỏ. Nó quản lý vòng đời của coroutine và
    cho phép điều khiển coroutine từ bên ngoài
    - Các trạng thái của job: New, Active, Cancelling, Cancelled, Completed
    - job.join() : tạm dừng cho đến khi job hoàn thành
    - job.cancel() : Hủy job
    - job.cancelAndJoin() : Hủy và đợi cho đến khi job hoàn thành
*/

fun main (){
    runBlocking {
        launch {
            val job1 = launch {
                try {
                    repeat(5) { i ->
                        println("Running $i")
                        delay(500)
                    }
                } finally {
                    println("job1: Cleanup before exit")
                }
            }
            val job2 = launch {
                try {
                    repeat(5) { i ->
                        println("Running $i")
                        delay(500)
                    }
                } finally {
                    println("job2: Cleanup before exit")
                }
            }
            delay(1000)
            job1.cancelAndJoin()
        }
    }
}
