package com.example.flowcoroutine.flow

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

/*
4. Flow context và các dispatcher hoạt động như thế nào với Flow?
    - dùng flowOn để thay đổi thread của flow
    - có thể emit dữ liệu ở một luồng và nhận nó ở luồng khác

*/

/*
fun main() = runBlocking {
    val myFlow = flow {
        emit("Hello from IO thread")
    }.flowOn(Dispatchers.IO) //emit trên IO thread

    lifecycleScope.launch(Dispatchers.Main) {
        myFlow.collect { value ->
            Log.d("FlowExample", value) // Nhận dữ liệu trên Main thread
        }
    }
}*/
