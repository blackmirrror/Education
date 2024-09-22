package ru.blackmirrror.androidsdk

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.blackmirrror.androidsdk.utils.ChargingWorker
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

        if (isNotificationPermissionRequired()) {
            requestPermissions()
        } else {
            setOneTimeWork()
        }

        appRouter.navigateTo(FirstScreen)
        setExit()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setOneTimeWork()
            }
        }
    }

    private fun setOneTimeWork() {
        WorkManager.getInstance(applicationContext)
            .enqueueUniqueWork(
                ChargingWorker.WORK_NAME,
                ExistingWorkPolicy.KEEP,
                ChargingWorker.makeRequest()
            )
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 101)
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

    private fun isNotificationPermissionRequired() =
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
}