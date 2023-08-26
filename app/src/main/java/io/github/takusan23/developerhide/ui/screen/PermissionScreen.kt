package io.github.takusan23.developerhide.ui.screen

import android.content.ClipData
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.takusan23.developerhide.R

/** 入力すべき ADB コマンド */
private const val ADB_COMMAND = "adb shell pm grant io.github.takusan23.developerhide android.permission.WRITE_SECURE_SETTINGS"

/**
 * 権限ください画面
 *
 * @param onCheckButtonClick チェックボタン押したとき
 * */
@Composable
fun PermissionScreen(onCheckButtonClick: () -> Unit) {
    val context = LocalContext.current

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp),
                painter = painterResource(id = R.drawable.ic_outline_usb_24),
                contentDescription = "usb"
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = "パソコンと接続して作業が必要です。",
                fontSize = 20.sp
            )
            Text(
                modifier = Modifier.padding(5.dp),
                text = "パソコンと接続して、USBデバッグを有効にした上で、ターミナル（シェル、コンソール、コマンドライン）で以下のコマンドを実行する必要があります。一部のROMではUSBデバッグ以外の設定を変更にする必要がある模様。",
                textAlign = TextAlign.Center
            )
            Surface(
                modifier = Modifier.padding(10.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(10.dp),
                    text = ADB_COMMAND,
                    maxLines = 2,
                    softWrap = false
                )
            }

            OutlinedButton(
                modifier = Modifier.padding(10.dp),
                onClick = {
                    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                    clipboardManager.setPrimaryClip(ClipData.newPlainText("", ADB_COMMAND))
                }
            ) { Text(text = "ADB コマンドをコピーする") }

            Button(
                onClick = onCheckButtonClick,
                modifier = Modifier.padding(10.dp)
            ) { Text(text = "権限チェック") }
        }
    }
}