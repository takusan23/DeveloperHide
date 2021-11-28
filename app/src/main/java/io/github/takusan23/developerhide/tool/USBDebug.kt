package io.github.takusan23.developerhide.tool

import android.content.ContentResolver
import android.content.Context
import android.provider.Settings
import androidx.preference.PreferenceManager

object USBDebug {

    /**
     * USBデバッグを有効、無効にする関数
     *
     * @param contentResolver Contextあれば取れる
     * @param enable 有効にするならtrue
     * */
    fun setUSBDebugSetting(contentResolver: ContentResolver, enable: Boolean) {
        Settings.Global.putLong(contentResolver, Settings.Global.ADB_ENABLED, if (enable) 1 else 0)
    }

    /**
     * 開発者向けオプション自体を有効、無効にする関数
     *
     * @param contentResolver Contextにあるやつ
     * @param enable 有効にするならtrue
     * */
    fun setDevelopmentSetting(contentResolver: ContentResolver, enable: Boolean) {
        Settings.Global.putLong(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, if (enable) 1 else 0)
    }

    /**
     * 開発者向けオプション自体を無効にする設定が有効になっているか
     *
     * @param context Context
     * @return 無効にする設定ならtrue
     * */
    fun isSetDevelopmentOptionHide(context: Context) = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("enable_development_hide", false) ?: false

}