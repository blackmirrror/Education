package ru.blackmirrror.dagger2.core.network

import ru.blackmirrror.dagger2.core.di.DaggerCoreComponent

object ApiFactory {

    private val daggerCoreComponent = DaggerCoreComponent.builder().build()

    fun createFirstApi(): FirstApi = daggerCoreComponent.firstApi

    fun createSecondApi(): SecondApi = daggerCoreComponent.secondApi
}