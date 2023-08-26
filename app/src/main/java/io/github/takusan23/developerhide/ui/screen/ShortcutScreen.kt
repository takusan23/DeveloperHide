package io.github.takusan23.developerhide.ui.screen

import android.content.pm.ResolveInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import io.github.takusan23.developerhide.applist.AppList
import io.github.takusan23.developerhide.tool.CreateShortcutTool

/**
 * ショートカット作成画面
 *
 * @param onClick 押したときに呼ばれる。引数はパッケージID
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShortcutScreen() {
    val context = LocalContext.current
    val appList = produceState<List<ResolveInfo>>(initialValue = listOf(), producer = { value = AppList.getAppListFromCategoryLauncher(context = context) })

    Scaffold(
        topBar = { LargeTopAppBar(title = { Text(text = "ショートカット作成画面") }) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            if (appList.value.isEmpty()) {
                LoadingScreen()
            } else {
                LazyColumn {
                    items(appList.value) { app ->
                        ShortcutAppList(
                            resolveInfo = app,
                            onClick = {
                                // ショートカット作成
                                CreateShortcutTool.createHomeScreenShortcut(context, app.activityInfo.packageName)
                            }
                        )
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
private fun ShortcutAppList(resolveInfo: ResolveInfo, onClick: () -> Unit) {
    val packageManager = LocalContext.current.packageManager
    val iconBitmap = remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(key1 = Unit) {
        iconBitmap.value = resolveInfo.loadIcon(packageManager).toBitmap().asImageBitmap()
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Transparent,
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            if (iconBitmap.value != null) {
                Image(
                    bitmap = iconBitmap.value!!,
                    modifier = Modifier.size(50.dp),
                    contentDescription = "icon"
                )
            } else {
                Spacer(modifier = Modifier.size(50.dp))
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            )
            {
                Text(
                    text = resolveInfo.loadLabel(packageManager)?.toString() ?: "不明",
                    fontSize = 20.sp
                )
                Text(text = resolveInfo.activityInfo.packageName)
            }
        }
    }
}