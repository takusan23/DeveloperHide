package io.github.takusan23.developerhide.tool

import android.content.ContentResolver
import android.provider.Settings

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

}