package ru.blackmirrror.dbnetworkpatterns.patterns

data class Car(
    var mark: String? = null,
    var color: String? = null,
    var carBody: String? = null,
    var seatsCount: Int? = null
): Transport()

abstract class Transport {
    var type: String? = null
}