package ru.blackmirrror.androidsdk

import androidx.fragment.app.FragmentManager
import ru.blackmirrror.core.FirstScreen
import ru.blackmirrror.core.Router
import ru.blackmirrror.core.Screen
import ru.blackmirrror.core.SecondScreen
import ru.blackmirrror.core.ThirdScreen
import ru.blackmirrror.first.FirstFragment
import ru.blackmirrror.second.SecondFragment
import ru.blackmirrror.third.ThirdFragment

class AppRouter(private val fragmentManager: FragmentManager) : Router {

    override fun navigateTo(screen: Screen) {
        val fragment = when (screen) {
            is FirstScreen -> FirstFragment()
            is SecondScreen -> SecondFragment()
            is ThirdScreen -> ThirdFragment()
        }

        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun goBack() {
        fragmentManager.popBackStack()
    }
}