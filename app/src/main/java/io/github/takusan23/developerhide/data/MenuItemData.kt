package io.github.takusan23.developerhide.data

/**
 * ホーム画面のメニューのデータクラス
 * */
data class MenuItemData(
    val name: String,
    val icon: Int,
    val description: String? = null,
    val route: String,
)