package io.github.takusan23.developerhide.tool

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object PermissionCheckTool {

    /**
     * WRITE_SECURE_SETTINGS 権限がある場合はtrueを返す
     *
     * @param context Context
     * @return 権限ある場合はtrue
     * */
    fun isGrantWriteSecureSettings(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_SECURE_SETTINGS) == PackageManager.PERMISSION_GRANTED
    }

}