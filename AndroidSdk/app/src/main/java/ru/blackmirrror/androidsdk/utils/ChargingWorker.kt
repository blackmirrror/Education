package ru.blackmirrror.androidsdk.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import ru.blackmirrror.androidsdk.R

class ChargingWorker(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    override fun doWork(): Result {
        createNotification()
        return Result.success()
    }

    private fun createNotification() {
        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("Изменение состояния устройства")
            .setContentText("Подключено к зарядному устройству")
            .setSmallIcon(R.drawable.baseline_charging_station_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    companion object {
        private const val CHANNEL_ID = "channel_charge_1"
        private const val CHANNEL_NAME = "channel_charge"
        private const val NOTIFICATION_ID = 1
        const val WORK_NAME = "work_charge"

        fun makeRequest() = OneTimeWorkRequestBuilder<ChargingWorker>()
            .setConstraints(getConstraints())
            .build()

        private fun getConstraints() = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
    }
}