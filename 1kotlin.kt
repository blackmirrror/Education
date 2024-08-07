

/** Задача 1 **/

// Постановка

data class Key( val field1: Int, var field2: String ) {
    var field3: String? = null
}
// Могут ли возникнуть какие-то проблемы, если мы будем использовать
// подобный класс в качестве ключа для HashMap?

// Решение

// Проблемы будут, потому что field3 не будет учитываться
// при сравнении объектов данного типа, так как его нет в конструкторе.
// И оно не будет учитываться в методах component, toString, equals и т.д.



/** Задача 2 **/

// Постановка

// Написать свой делегат, который будет кешировать время запуска приложения.
// Раз в 3 секунды выводить закешированное значение в логи Не в UI потоке.

// Решение

import kotlinx.coroutines.*
import kotlin.reflect.KProperty

class CachedStartTimeDelegate {

    private var startTime: Long = System.currentTimeMillis()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        coroutineScope.launch {
            while (isActive) {
                delay(3000)
                logStartTime()
            }
        }
    }

    private fun logStartTime() {
        println("Cached Start Time: $startTime")
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        return startTime
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        startTime = value
    }
}


class App : Application() {

    private var startTime by CachedStartTimeDelegate()

    override fun onCreate() {
        super.onCreate()
        startTime = System.currentTimeMillis()
    }
}


/** Задача 3 **/

// Постановка

// Написать extention-функцию для List,
// которая в рантайме будет искать Int в списке типа Any и возвращать его.
// Заранее подготовить список, наполненный разными классами(5-10 шт будет достаточно).
// По нажатию на кнопку выводить результат в логи (не использовать рефлексию).

// Решение

fun main() {
    val mixedList: List<Any> = listOf(
        "String",
        42,
        3.14,
        true,
        listOf(1, 2, 3)
    )
    println(mixedList.findFirstInt())
}

fun List<Any>.findFirstInt(): Int? {
    for (item in this) {
        if (item is Int) {
            return item
        }
    }
    return null
}

/** Задача 4 **/

// Постановка

// Написать шейкерную сортировку для List<Int?>.
// Учесть кейсы, когда переданный массив = null,
// некоторые эл-ты массива = null - пушить такие эл-ты в конец сортированного списка.

// Решение

fun main() {
    val list = ArrayList<Int?>()
    list.add(null)
    list.add(null)
    list.add(42)
    list.add(null)
    list.add(1)
    list.add(null)
    list.add(18)
    list.add(null)

    shakerSort(list)
    println(list)
}

fun shakerSort(list: MutableList<Int?>?) {
    if (list == null) return

    val n = list.size
    var start = 0
    var end = n - 1
    var swapped: Boolean

    do {
        swapped = false
        for (i in start until end) {
            if (compare(list[i], list[i + 1]) > 0) {
                list.swap(i, i + 1)
                swapped = true
            }
        }
        end--

        if (!swapped) break
        swapped = false

        for (i in end downTo start) {
            if (compare(list[i], list[i + 1]) > 0) {
                list.swap(i, i + 1)
                swapped = true
            }
        }
        start++

    } while (swapped)

    val nonNullList = list.filterNotNull()
    val nullCount = list.size - nonNullList.size
    list.clear()
    list.addAll(nonNullList)
    list.addAll(List(nullCount) { null })
}

private fun compare(a: Int?, b: Int?): Int {
    return when {
        a == null && b == null -> 0
        a == null -> 1
        b == null -> -1
        else -> a.compareTo(b)
    }
}

private fun MutableList<Int?>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}
