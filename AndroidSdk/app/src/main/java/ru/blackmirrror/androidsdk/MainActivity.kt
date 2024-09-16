package ru.blackmirrror.androidsdk

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.blackmirrror.core.FirstScreen
import ru.blackmirrror.core.Router
import ru.blackmirrror.first.FirstFragment

class MainActivity : AppCompatActivity() {

    private val appRouter: Router by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        appRouter.navigateTo(FirstScreen)

        setExit()
    }

    private fun setExit() {
        onBackPressedDispatcher.addCallback(this) {
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (fragment is FirstFragment) {
                finish()
            } else {
                supportFragmentManager.popBackStack()
            }
        }
    }
}