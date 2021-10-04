package io.github.takusan23.developerhide.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import androidx.preference.PreferenceManager

private val DarkColorPalette = darkColors(
    primary = Green200,
    primaryVariant = Green700,
    secondary = Green500
)

private val LightColorPalette = lightColors(
    primary = Green200,
    primaryVariant = Green700,
    secondary = Green500

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

/**
 * てーま
 * @param isUseMaterialYou Material Youを利用する場合はtrue。なおAndroid 12以前の場合はtrueでも利用しません
 * */
@Composable
fun DeveloperHideTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isUseMaterialYou: Boolean = false,
    content: @Composable() () -> Unit,
) {
    val context = LocalContext.current
    val appColors = if (darkTheme) DarkColorPalette else LightColorPalette

    // Android 12以降で
    val system_accent1_500 = 0x0106003e // android.R.color.system_accent1_500
    val system_accent1_700 = 0x01060040 // android.R.color.system_accent1_700
    val system_accent1_200 = 0x0106003b // android.R.color.system_accent1_200
    val materialYouColors = appColors.copy(
        primary = Color(ResourcesCompat.getColor(context.resources, system_accent1_500, context.theme)),
        primaryVariant = Color(ResourcesCompat.getColor(context.resources, system_accent1_700, context.theme)),
        secondary = Color(ResourcesCompat.getColor(context.resources, system_accent1_200, context.theme)),
    )

    MaterialTheme(
        colors = if (Build.VERSION.SDK_INT >= 31 && isUseMaterialYou) materialYouColors else appColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}