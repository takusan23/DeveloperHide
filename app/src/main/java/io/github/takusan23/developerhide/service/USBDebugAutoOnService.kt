package io.github.takusan23.developerhide.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import io.github.takusan23.developerhide.R
import io.github.takusan23.developerhide.tool.USBDebug

/**
 * ShortcutHostActivity起動中はそのまま常駐して、終了したらUSBデバッグをOFFにするサービス
 * */
class USBDebugAutoOnService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "usbdebug_auto_on"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(NotificationChannel(channelId, "USBデバックを戻すサービス", NotificationManager.IMPORTANCE_LOW))
        }
        val notification = NotificationCompat.Builder(this, channelId).apply {
            setContentTitle("USBデバックを戻すサービス")
            setContentText("アプリが終了したらUSBデバッグが有効になります")
            setSmallIcon(R.drawable.android_developer_hide).build()
            // すぐに通知を出す
            foregroundServiceBehavior = NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE
        }.build()

        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        // USBデバッグを有効に戻す
        USBDebug.setUSBDebugSetting(contentResolver, true)
        // 開発者向けオプションもOFFにする設定なら戻す
        if (USBDebug.isSetDevelopmentOptionHide(this)) {
            USBDebug.setDevelopmentSetting(contentResolver, true)
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null;
    }

}

