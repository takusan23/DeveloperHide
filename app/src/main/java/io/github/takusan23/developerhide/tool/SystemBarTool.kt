package io.github.takusan23.developerhide.tool

import android.app.Activity
import android.view.Window
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

object SystemBarTool {

    /**
     * ステータスバーとナビゲーションバーの色を変更する
     *
     * @param window Window
     * @param color 色
     * */
    fun setSystemBarColor(window: Window, color: Int) {
        window.statusBarColor = color
        window.navigationBarColor = color
    }


}

/**
 * setSystemBarColorのCompose版
 * */
@Composable
fun SetSystemBarColorCompose() {
    val context = LocalContext.current
    val theme = MaterialTheme.colors.primary.copy(0.2f)
    LaunchedEffect(Unit) {
        if (context is Activity) {
            SystemBarTool.setSystemBarColor(context.window, theme.toArgb())
        }
    }
}
