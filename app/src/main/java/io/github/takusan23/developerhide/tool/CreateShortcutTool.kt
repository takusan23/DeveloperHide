package io.github.takusan23.developerhide.tool

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
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
        val iconBitmap = createAppIconDrawable(context, packageManager.getApplicationInfo(packageName, 0).loadIcon(packageManager))
        val appName = packageManager.getApplicationInfo(packageName, 0).loadLabel(packageManager)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 7.1 以降のみ対応
            val shortcutManager = context.getSystemService(Context.SHORTCUT_SERVICE) as ShortcutManager
            // サポート済みのランチャーだった
            if (shortcutManager.isRequestPinShortcutSupported) {
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

    /**
     * アイコンの Bitmap を作成する
     *
     * @param context [Context]
     * @param drawable アイコンの[Drawable]。
     * @see [android.content.pm.ActivityInfo.loadIcon]
     * @return アイコン画像。テーマアイコンに対応していればテーマアイコンを返す
     */
    private fun createAppIconDrawable(context: Context, drawable: Drawable): Bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && drawable is AdaptiveIconDrawable && drawable.monochrome != null) {
        val (backgroundColor, foregroundColor) = getThemeIconColorPair(context)
        val monochromeIcon = drawable.monochrome!!.apply {
            mutate()
            setTint(foregroundColor)
        }
        AdaptiveIconDrawable(ColorDrawable(backgroundColor), monochromeIcon)
    } else {
        drawable
    }.toBitmap()

    /**
     * テーマアイコンの時のバックグラウンド・フォアグラウンドの色を取得する
     * https://cs.android.com/android/platform/superproject/+/refs/heads/master:frameworks/libs/systemui/iconloaderlib/src/com/android/launcher3/icons/ThemedIconDrawable.java
     *
     * @param context [Context]
     * @return バックグラウンド・フォアグラウンドのPair
     */
    @RequiresApi(Build.VERSION_CODES.S)
    private fun getThemeIconColorPair(context: Context): Pair<Int, Int> = if (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
        ContextCompat.getColor(context, android.R.color.system_neutral1_800) to ContextCompat.getColor(context, android.R.color.system_accent1_100)
    } else {
        ContextCompat.getColor(context, android.R.color.system_accent1_100) to ContextCompat.getColor(context, android.R.color.system_neutral2_700)
    }

}