package io.github.takusan23.developerhide.tool

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import androidx.core.graphics.drawable.toBitmap
import io.github.takusan23.developerhide.ShortcutHostActivity

object CreateShortcutTool {

    /**
     * ホーム画面にショートカットを追加する
     *
     * @param context Context
     * @param packageName パッケージID
     * */
    fun createHomeScreenShortcut(context: Context, packageName: String) {
        // ショートカットを押したときのインテント
        val intent = Intent(context, ShortcutHostActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            putExtra("package_name", packageName)
        }
        // パッケージIDからアプリ情報を得る
        val packageManager = context.packageManager
        val iconBitmap = packageManager.getApplicationIcon(packageName).toBitmap()
        val appName = packageManager.getApplicationInfo(packageName, 0).loadLabel(packageManager)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 7.1 以降のみ対応
            val shortcutManager = context.getSystemService(Context.SHORTCUT_SERVICE) as ShortcutManager
            // サポート済みのランチャーだった
            if (shortcutManager.isRequestPinShortcutSupported) {
                // Icon.createWithBitmap()
                val icon = Icon.createWithBitmap(iconBitmap)
                val shortcut = ShortcutInfo.Builder(context, appName.toString()).apply {
                    setShortLabel(appName)
                    setLongLabel(appName)
                    setIcon(icon)
                    setIntent(intent)
                }.build()
                shortcutManager.requestPinShortcut(shortcut, null)
            }
        } else {
            // ショートカット作成ブロードキャストインテント
            val broadcastIntent = Intent("com.android.launcher.action.INSTALL_SHORTCUT").apply {
                putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent)
                putExtra(Intent.EXTRA_SHORTCUT_NAME, appName.toString())
                putExtra(Intent.EXTRA_SHORTCUT_ICON, iconBitmap)
            }
            // ブロードキャスト送信
            context.sendBroadcast(broadcastIntent)
        }
    }
}