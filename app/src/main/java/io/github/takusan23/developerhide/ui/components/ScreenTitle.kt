package io.github.takusan23.developerhide.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * タイトル部分
 * */
@Composable
fun ScreenTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(start = 10.dp, top = 100.dp, end = 10.dp, bottom = 10.dp),
        fontSize = 30.sp
    )
}
