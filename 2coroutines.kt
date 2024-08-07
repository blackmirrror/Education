/** Задача 1 **/

// Постановка

// Напишите аналоги операторов throttleFisrt и throttleLatest из RxJava для Flow.

// Решение

fun <T> Flow<T>.throttleFirst(windowDuration: Long): Flow<T> = flow {
    var windowStartTime = System.currentTimeMillis()
    var emitted = false
    collect { value ->
        val currentTime = System.currentTimeMillis()
        val delta = currentTime - windowStartTime
        if (delta >= windowDuration) {
            windowStartTime += delta / windowDuration * windowDuration
            emitted = false
        }
        if (!emitted) {
            emit(value)
            emitted = true
        }
    }
}

fun <T> Flow<T>.throttleLast(windowDuration: Long): Flow<T> = flow {
    var windowStartTime = System.currentTimeMillis()
    var lastValue: T? = null

    collect { value ->
        val currentTime = System.currentTimeMillis()
        val delta = currentTime - windowStartTime
        if (delta >= windowDuration) {
            lastValue?.let {
                emit(value)
            }
            lastValue = null
            windowStartTime += delta / windowDuration * windowDuration
        }

        lastValue = value
    }
}

suspend fun main() {
    val flow = (1..10).asFlow().onEach { delay(200) }
    flow.throttleLast(500).collect {
        println(it)
    }
}