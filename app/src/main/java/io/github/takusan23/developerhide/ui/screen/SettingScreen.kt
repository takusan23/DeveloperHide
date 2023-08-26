package io.github.takusan23.developerhide.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.takusan23.developerhide.R
import io.github.takusan23.developerhide.ui.components.SettingTextOnlyItem
import io.github.takusan23.developerhide.ui.components.SwitchSettingItem

/**
 * 設定画面
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = { LargeTopAppBar(title = { Text(text = "設定画面") }) }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                SwitchSettingItem(
                    preferenceKey = "enable_material_you",
                    name = "Material You（動的テーマ）を利用する",
                    icon = R.drawable.ic_outline_format_paint_24,
                    description = "Android 12以前では動きません"
                )
            }
            item {
                SwitchSettingItem(
                    preferenceKey = "enable_development_hide",
                    name = "「開発者向けオプション」自体を無効にする",
                    icon = R.drawable.ic_outline_developer_mode_24,
                    description = "開発者向けオプションのチェックに引っかかる場合はどうぞ"
                )
            }
            item {
                SettingTextOnlyItem(
                    name = "このアプリについて",
                    icon = R.drawable.ic_outline_info_24,
                    description = "オープンソースです",
                    onClick = { onNavigate("about") }
                )
            }
        }
    }
}