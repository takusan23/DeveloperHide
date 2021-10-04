package io.github.takusan23.developerhide.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.takusan23.developerhide.R

/**
 * 権限ください画面
 *
 * @param onCheckButtonClick チェックボタン押したとき
 * */
@Composable
fun PermissionScreen(onCheckButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_outline_usb_24),
            contentDescription = "usb",
            modifier = Modifier
                .size(50.dp)
                .padding(5.dp)
        )
        Text(
            text = "パソコンと接続する作業が必要です。",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(5.dp)
        )
        Text(
            text = "パソコンと接続して、USBデバッグを有効にした上で、ターミナルで以下のコマンドを実行する必要があります ",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(5.dp)
        )
        Text(
            text = "adb shell pm grant io.github.takusan23.developerhide android.permission.WRITE_SECURE_SETTINGS",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp),
        )
        Button(
            onClick = onCheckButtonClick,
            modifier = Modifier
                .padding(10.dp),
            content = {
                Text(text = "権限チェック")
            }
        )
    }
}