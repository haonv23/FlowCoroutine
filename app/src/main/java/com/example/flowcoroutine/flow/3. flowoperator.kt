package com.example.flowcoroutine.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/* 3. Các phương thức toán tử (operator) phổ biến của Flow và ứng dụng của chúng?
    - map, transform, filter, take, zip

 */
@OptIn(ExperimentalCoroutinesApi::class)
fun main(): Unit = runBlocking{
    launch {
        flow{
            repeat(10) {
                delay(1000)
                emit(it)
            }
        }.map {// thay đổi giá trị
            it * it
        }.transform {//thay đổi, phát nhiều giá trị
            emit(it)
            emit(it)
        }.filter {//lọc giá trị
            it % 2 == 0
        }.take(10)//lấy 10 giá trị
            .collect { value ->
                println("Received value: $value")
            }

        val numbers = flowOf(1, 2, 3)
        val words = flowOf("One", "Two", "Three")

        numbers.zip(words) { num, word -> "$num -> $word" } // kết hợp phát giá trị theo cặp
            .collect { println(it) }

        val flow1 = flowOf(1, 2, 3).onEach { delay(100) }
        val flow2 = flowOf("A", "B", "C", "D").onEach { delay(200) }


        flow1.combine(flow2) { num, letter -> "$num - $letter" }//phát giá trị ngay khi một trong hai Flow có thay đổi.
            .collect { println(it) }


        flowOf(1, 2, 3)
            .flatMapConcat { getFlow(it) } //Chuyển Flow<Flow<T>> thành Flow<T> và xử lý tuần tự.
            .collect { println(it) }
    }
}

fun getFlow(n: Int) = flow {
    emit("$n: A")
    delay(100)
    emit("$n: B")
}