package ru.blackmirrror.dagger2.home.domain

internal interface SecondRepository {

    suspend fun getSecondResult(): Any
}