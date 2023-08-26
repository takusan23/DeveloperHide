package io.github.takusan23.developerhide

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.takusan23.developerhide.service.USBDebugAutoOnService
import io.github.takusan23.developerhide.tool.ActivityCloseContracts
import io.github.takusan23.developerhide.tool.USBDebug

/**
 * ほかアプリを起動する前にUSBデバッグをOFFにするのと、アプリ終わったときにONに戻すためのAcitivity
 *
 * 必要な値
 * package_name string 起動したいアプリのパッケージID
 *
 * */
class ShortcutHostActivity : AppCompatActivity() {

    private val activityCloseCallback = registerForActivityResult(ActivityCloseContracts()) {
        // アプリ終了時にUSBデバッグをONに戻す
        USBDebug.setUSBDebugSetting(contentResolver, true)
        // 開発者向けオプションもOFFにする設定なら戻す
        if (USBDebug.isSetDevelopmentOptionHide(this)) {
            USBDebug.setDevelopmentSetting(contentResolver, true)
        }
        // バックキーで戻ってきたらここに来る。アプリ閉じる
        finishAndRemoveTask()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // USBデバッグ無効化
        USBDebug.setUSBDebugSetting(contentResolver, false)
        // 開発者向けオプションもOFFにする設定なら無効に
        if (USBDebug.isSetDevelopmentOptionHide(this)) {
            USBDebug.setDevelopmentSetting(contentResolver, false)
        }

        // USBデバッグを無効にしないといけないアプリを起動
        val packageName = intent.getStringExtra("package_name")!!
        println(packageName)
        val launchIntent = packageManager.getLaunchIntentForPackage(packageName)?.apply {
            categories?.add(Intent.CATEGORY_LAUNCHER) // ランチャーから起動した時はこれが付与されるので真似するように
            flags = 0 // 勝手に Intent.FLAG_ACTIVITY_NEW_TASK を指定するので消す
        }
        activityCloseCallback.launch(launchIntent)

        // サービス起動
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(Intent(this, USBDebugAutoOnService::class.java))
        } else {
            startService(Intent(this, USBDebugAutoOnService::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // サービス側のonDestroyでUSBデバッグをOFFにする
        stopService(Intent(this, USBDebugAutoOnService::class.java))
    }


}