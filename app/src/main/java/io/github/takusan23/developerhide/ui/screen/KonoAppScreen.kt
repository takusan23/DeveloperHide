package io.github.takusan23.developerhide.ui.screen

import android.content.pm.PackageInfo
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import io.github.takusan23.developerhide.R
import io.github.takusan23.developerhide.ui.components.ScreenTitle
import io.github.takusan23.developerhide.ui.components.SettingTextOnlyItem

/**
 * このアプリについて画面
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KonoAppScreen() {
    val appInfo = getCurrentAppInfo()

    Column(modifier = Modifier.fillMaxSize()) {
        ScreenTitle(title = "このアプリについて")
        Divider()
        LazyColumn(
            content = {
                item { SettingTextOnlyItem(name = appInfo.name, icon = R.drawable.android_developer_hide, description = "アプリ名", onClick = { }) }
                item { SettingTextOnlyItem(name = appInfo.version, icon = null, description = "バージョン", onClick = { }) }
                item { SettingTextOnlyItem(name = appInfo.packageName, icon = null, description = "パッケージID", onClick = { }) }
            }
        )
    }
}

/**
 * アプリ情報を返す
 * */
@Composable
private fun getCurrentAppInfo(): AboutAppInfo {
    val context = LocalContext.current
    val packageManager = context.packageManager
    val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
    return remember {
        AboutAppInfo(
            packageName = packageInfo.applicationInfo.packageName,
            name = packageInfo.applicationInfo.loadLabel(packageManager).toString(),
            version = packageInfo.versionName,
        )
    }
}

private data class AboutAppInfo(
    val name: String,
    val version: String,
    val packageName: String,
)