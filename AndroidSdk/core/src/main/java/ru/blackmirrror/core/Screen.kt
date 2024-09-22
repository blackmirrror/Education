package ru.blackmirrror.core

sealed class Screen

data object FirstScreen : Screen()
data object SecondScreen : Screen()
data object ThirdScreen : Screen()