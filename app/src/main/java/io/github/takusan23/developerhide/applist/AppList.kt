package io.github.takusan23.developerhide.applist

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AppList {

    /**
     * ランチャーから起動可能なアプリを一覧にして返す。
     *
     * なんとなくコルーチン
     *
     * ちなみにAndroid 11以降Package Visibilityの仕様が導入されて、マニフェストに追記しないと何も返ってきません。
     *
     * @param context Context
     * @return アプリ一覧
     * */
    suspend fun getAppListFromCategoryLauncher(context: Context): List<ResolveInfo> = withContext(Dispatchers.Default) {
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        return@withContext context.packageManager.queryIntentActivities(mainIntent, 0)
    }

}