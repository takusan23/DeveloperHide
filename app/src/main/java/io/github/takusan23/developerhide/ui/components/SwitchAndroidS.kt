package io.github.takusan23.developerhide.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Android 12なスイッチを作る
 *
 * @param modifier Modifier
 * @param value true / false
 * @param onValueChange スイッチ押したとき
 * */
@Composable
fun SwitchAndroidS(modifier: Modifier = Modifier, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    val disableForegroundColor = Color(0xFFb7a999)
    val disableBackgroundColor = Color(0xFF74695c)
    Surface(
        shape = RoundedCornerShape(50),
        color = if (checked) MaterialTheme.colors.primaryVariant else disableBackgroundColor,
        modifier = modifier
            .width(width = 60.dp)
            .height(height = 30.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onCheckedChange(!checked) }
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .align(alignment = if (checked) Alignment.TopEnd else Alignment.TopStart)
                    .padding(all = 5.dp)
                    .background(
                        color = if (checked) MaterialTheme.colors.secondary else disableForegroundColor,
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}

@Preview
@Composable
fun SwitchAndroidSPrev() {
    MaterialTheme {
        val checked = remember { mutableStateOf(false) }
        SwitchAndroidS(checked = checked.value, onCheckedChange = { checked.value = it })
    }
}