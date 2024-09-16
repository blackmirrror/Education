package ru.blackmirrror.core

import androidx.fragment.app.Fragment

interface Router {
    fun navigateTo(screen: Screen)
    fun goBack()
}