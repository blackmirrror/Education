### Примеры работы RX Java

##### Практика



##### Задания

###### 1.1

Самая популярная, на конфликт subscribeOn и observeOn приходится 80% задач RX. Какой результат будет в логе?

**Решение**

``` kotlin
Observable.timer(1000L, TimeUnit.MILLISECONDS, Schedulers.newThread())
    .subscribeOn(Schedulers.io()) // не имеет значения, потому что в timer Schedulers.newThread()
    .map {
        printt("map")  // Schedulers.newThread()
    }
    .doOnSubscribe {
        printt("onSubscribe") // Schedulers.computation(), потому что отрабатывает на этапе подписки
    }
    .subscribeOn(Schedulers.computation())
    .observeOn(Schedulers.single())
    .flatMap {
        printt("flatMap") // Schedulers.single()
        Observable.just(it)
            .subscribeOn(Schedulers.io())
            .map {
                printt("inner map") // Schedulers.io()
            }
    }
    .subscribe {
        printt("subscribe")  // Schedulers.io(), так как в flatMap он указан
    }
    
private fun printt(t: String, v: Long? = null) {
    println(t + " item ${v ?: ""} on: " + Thread.currentThread().name + " " + Thread.currentThread().id)
}
```

###### 1.2 

Какой результат будет в логе? Как переписать, чтоб все вывести (2 варианта есть)

``` kotlin
val s = PublishSubject.create<String>()
s.onNext("1")
s.onNext("2")
s.onNext("3")
s.subscribe {
    println(it)
}
```

**Решение**

Пример из задания не возвращает ничего, так как PublishSubject не имеет буфера

Мы можем использовать ReplaySubject с буфером.

``` kotlin
val s = ReplaySubject.createWithSize<String>(3)
```

Или просто подписаться перед эмитом значений.

``` kotlin
val s = PublishSubject.create<String>()
s.subscribe {
    println(it)
}
s.onNext("1")
s.onNext("2")
s.onNext("3")
```