package io.github.takusan23.developerhide.ui.screen

import android.content.pm.ResolveInfo
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import io.github.takusan23.developerhide.applist.AppList
import io.github.takusan23.developerhide.tool.CreateShortcutTool
import io.github.takusan23.developerhide.ui.components.ScreenTitle

/**
 * ショートカット作成画面
 *
 * @param onClick 押したときに呼ばれる。引数はパッケージID
 * */
@ExperimentalMaterialApi
@Composable
fun ShortcutScreen() {
    val context = LocalContext.current
    val appList = produceState<List<ResolveInfo>>(initialValue = listOf(), producer = { value = AppList.getAppListFromCategoryLauncher(context = context) })

    if (appList.value.isEmpty()) {
        LoadingScreen()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                content = {
                    item { ScreenTitle(title = "ショートカット作成画面") }
                    item { Divider() }
                    items(appList.value) { app ->
                        ShortcutAppList(
                            resolveInfo = app,
                            onClick = {
                                // ショートカット作成
                                CreateShortcutTool.createHomeScreenShortcut(context, app.activityInfo.packageName)
                            }
                        )
                    }
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun ShortcutAppList(resolveInfo: ResolveInfo, onClick: () -> Unit) {
    val packageManager = LocalContext.current.packageManager

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Transparent,
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            Image(
                bitmap = resolveInfo.loadIcon(packageManager).toBitmap().asImageBitmap(),
                modifier = Modifier.size(50.dp),
                contentDescription = "icon"
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp))
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