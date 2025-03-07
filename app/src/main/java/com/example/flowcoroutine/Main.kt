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


flow
1. Flow là gì và nó khác gì so với các hàm suspend thông thường?

2. Các builder phổ biến để tạo Flow là gì?

3. Các phương thức toán tử (operator) phổ biến của Flow và ứng dụng của chúng?

4. Flow context và các dispatcher hoạt động như thế nào với Flow?

5. StateFlow và SharedFlow là gì và sự khác biệt giữa chúng?

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