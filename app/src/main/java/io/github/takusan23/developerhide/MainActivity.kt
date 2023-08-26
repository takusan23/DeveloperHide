package io.github.takusan23.developerhide

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.preference.PreferenceManager
import io.github.takusan23.developerhide.tool.PermissionCheckTool
import io.github.takusan23.developerhide.ui.screen.HomeScreen
import io.github.takusan23.developerhide.ui.screen.KonoAppScreen
import io.github.takusan23.developerhide.ui.screen.PermissionScreen
import io.github.takusan23.developerhide.ui.screen.SettingScreen
import io.github.takusan23.developerhide.ui.screen.ShortcutScreen
import io.github.takusan23.developerhide.ui.theme.DeveloperHideTheme

/**
 * Composeで画面遷移してます
 *
 * 初回起動時にターミナルに入力するコマンド
 *
 * adb shell pm grant io.github.takusan23.developerhide android.permission.WRITE_SECURE_SETTINGS
 *
 * MIUI（Xiaomi）ユーザーは、USBデバッグ（セキュリティ設定）も有効にする必要がある模様
 *
 * */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val prefSetting = remember { PreferenceManager.getDefaultSharedPreferences(context) }
            val isEnableMaterialYou = remember { prefSetting.getBoolean("enable_material_you", false) }

            DeveloperHideTheme(isUseMaterialYou = isEnableMaterialYou) {

                // ステータスバーとナビゲーションバーの色
                // SetSystemBarColorCompose()

                // ルーティング
                val navController = rememberNavController()
                val startPage = remember { if (!PermissionCheckTool.isGrantWriteSecureSettings(context)) "permission" else "home" }
                NavHost(navController = navController, startDestination = startPage) {

                    // 権限ください画面
                    composable("permission") {
                        PermissionScreen(onCheckButtonClick = {
                            if (PermissionCheckTool.isGrantWriteSecureSettings(context)) {
                                navController.navigate("home")
                            } else {
                                Toast.makeText(context, "権限が付与されていません", Toast.LENGTH_SHORT).show()
                            }
                        })
                    }

                    // ホーム画面
                    composable("home") {
                        HomeScreen(onNavigate = { navController.navigate(it) })
                    }

                    // ショートカット作成画面
                    composable("shortcut") {
                        ShortcutScreen()
                    }

                    // 設定画面
                    composable("setting") {
                        SettingScreen(onNavigate = { navController.navigate("about") })
                    }

                    // このアプリについて
                    composable("about") {
                        KonoAppScreen()
                    }
                }
            }
        }
    }
}
