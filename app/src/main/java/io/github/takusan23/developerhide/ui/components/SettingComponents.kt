package io.github.takusan23.developerhide.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.preference.PreferenceManager

/**
 * スイッチの設定
 *
 * @param name タイトル
 * @param icon アイコン
 * @param description 説明
 * @param preferenceKey 設定のキー
 * */
@ExperimentalMaterialApi
@Composable
fun SwitchSettingItem(
    preferenceKey: String,
    name: String,
    icon: Int,
    description: String? = null,
) {
    val context = LocalContext.current
    val prefSetting = remember { PreferenceManager.getDefaultSharedPreferences(context) }
    val settingValue = remember { mutableStateOf(prefSetting.getBoolean(preferenceKey, false)) }

    fun setValue(value: Boolean) {
        settingValue.value = value
        prefSetting.edit { putBoolean(preferenceKey, value) }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Transparent,
        onClick = { setValue(!settingValue.value) }
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
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
            SwitchAndroidS(
                modifier = Modifier
                    .padding(5.dp)
                    .align(alignment = Alignment.CenterVertically),
                checked = settingValue.value,
                onCheckedChange = { after -> setValue(after) },
            )
        }
    }
}

/**
 * テキストのみの設定項目
 *
 * @param name タイトル
 * @param icon アイコン
 * @param description 説明
 * @param onClick 押したとき
 * */
@ExperimentalMaterialApi
@Composable
fun SettingTextOnlyItem(
    name: String,
    icon: Int?,
    description: String?,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Transparent,
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    modifier = Modifier
                        .size(30.dp)
                        .align(alignment = Alignment.CenterVertically),
                    contentDescription = ""
                )
            }
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