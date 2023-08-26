package io.github.takusan23.developerhide.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import io.github.takusan23.developerhide.R
import io.github.takusan23.developerhide.ui.components.SettingTextOnlyItem

/**
 * このアプリについて画面
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KonoAppScreen() {
    val appInfo = getCurrentAppInfo()

    Scaffold(
        topBar = { LargeTopAppBar(title = { Text(text = "このアプリについて") }) }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                SettingTextOnlyItem(
                    name = appInfo.name,
                    icon = R.drawable.android_developer_hide,
                    description = "アプリ名"
                )
            }
            item {
                SettingTextOnlyItem(
                    name = appInfo.version,
                    icon = null,
                    description = "バージョン"
                )
            }
            item {
                SettingTextOnlyItem(
                    name = appInfo.packageName,
                    icon = null,
                    description = "パッケージID"
                )
            }
        }
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