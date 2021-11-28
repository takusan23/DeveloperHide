package io.github.takusan23.developerhide.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.takusan23.developerhide.R
import io.github.takusan23.developerhide.ui.components.ScreenTitle
import io.github.takusan23.developerhide.ui.components.SettingTextOnlyItem
import io.github.takusan23.developerhide.ui.components.SwitchSettingItem

/**
 * 設定画面
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingScreen(onNavigate: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            content = {
                item {
                    ScreenTitle(title = "設定画面")
                    Divider()
                    SwitchSettingItem(
                        preferenceKey = "enable_material_you",
                        name = "Material You（動的テーマ）を利用する",
                        icon = R.drawable.ic_outline_format_paint_24,
                        description = "Android 12以前では動きません"
                    )
                    SwitchSettingItem(
                        preferenceKey = "enable_development_hide",
                        name = "「開発者向けオプション」自体を無効にする",
                        icon = R.drawable.ic_outline_developer_mode_24,
                        description = "開発者向けオプションのチェックに引っかかる場合はどうぞ"
                    )
                    SettingTextOnlyItem(
                        name = "このアプリについて",
                        icon = R.drawable.ic_outline_info_24,
                        description = "オープンソースです",
                        onClick = { onNavigate("about") }
                    )
                }
            }
        )
    }
}