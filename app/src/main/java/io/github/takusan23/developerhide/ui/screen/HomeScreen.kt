package io.github.takusan23.developerhide.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.takusan23.developerhide.R
import io.github.takusan23.developerhide.data.MenuItemData

/**
 * ホーム画面
 *
 * @param onNavigate ナビゲート
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {

    val menuItem = remember {
        mutableListOf(
            MenuItemData("ショートカット作成", R.drawable.ic_outline_widgets_24, "USBデバッグを自動でON/OFF", "shortcut"),
            MenuItemData("設定", R.drawable.ic_outline_settings_24, "ライセンスなど", "setting"),
        )
    }

    Scaffold(
        topBar = { LargeTopAppBar(title = { Text(text = "ホーム画面") }) }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(menuItem) { item ->
                HomeScreenMenuItem(
                    name = item.name,
                    icon = item.icon,
                    description = item.description,
                    onClick = { onNavigate(item.route) }
                )
            }
        }
    }
}

@Composable
private fun HomeScreenMenuItem(name: String, icon: Int, description: String?, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Transparent
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            Icon(
                painter = painterResource(id = icon),
                modifier = Modifier
                    .size(30.dp)
                    .align(alignment = Alignment.CenterVertically),
                contentDescription = ""
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                )
                Text(text = description ?: "")
            }
        }
    }
}