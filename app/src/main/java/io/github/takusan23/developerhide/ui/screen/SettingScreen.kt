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
@ExperimentalMaterialApi
@Composable
fun SettingScreen(onNavigate: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            content = {
                item { ScreenTitle(title = "設定画面") }
                item { Divider() }
                item {
                    SwitchSettingItem(
                        preferenceKey = "enable_material_you",
                        name = "Material You（動的テーマ）を利用する",
                        icon = R.drawable.ic_outline_format_paint_24,
                        description = "Android 12以前では動きません"
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
        )
    }
}