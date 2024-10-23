package ru.blackmirrror.dagger2.home.domain

internal interface FirstRepository {

    suspend fun getFirstResult(): Any
}