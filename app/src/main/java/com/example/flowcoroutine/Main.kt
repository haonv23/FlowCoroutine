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


4. Giải thích sự khác biệt giữa launch, async và runBlocking.
    - launch:
        + Trả về một job
        + Không chặn thread
        + Xử lý ngoại lệ: Truyền lên CoroutineScope
    - async:
        + Trả về một Deferred
        + Không chặn thread
        + Xử lý ngoại lệ: Khi gọi await(), không khi khởi tạo
    - runBlocking:
        + Trả về Unit
        + Chặn thread
        + Ngay lập tức, như code đồng bộ bình thường

5. Job là gì? Làm thế nào để quản lý vòng đời của coroutine?
    - Đại diện cho một công việc có thể hủy bỏ. Nó quản lý vòng đời của coroutine và
    cho phép điều khiển coroutine từ bên ngoài
    - Các trạng thái của job: New, Active, Cancelling, Cancelled, Completed
    - job.join() : tạm dừng cho đến khi job hoàn thành
    - job.cancel() : Hủy job
    - job.cancelAndJoin() : Hủy và đợi cho đến khi job hoàn thành

6. Flow là gì và làm thế nào để sử dụng nó với coroutine?
     - Là một API được thiết kế để xử lý luồng dữ liệu bất đồng bộ trong Kotlin Coroutines
     phát ra nhiều giá trị tuần tự theo thời gian
     - Chỉ phát dữ liệu khi có collector thu thập
7. Giải thích cơ chế hủy và xử lý lỗi trong coroutine


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