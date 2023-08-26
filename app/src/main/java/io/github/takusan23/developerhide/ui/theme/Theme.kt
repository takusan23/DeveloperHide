package io.github.takusan23.developerhide.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorPalette = darkColorScheme(
    primary = Green200,
    secondary = Green500,
    tertiary = Green700,
)

private val LightColorPalette = lightColorScheme(
    primary = Green200,
    secondary = Green700,
    tertiary = Green500

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
    val isAndroidS = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colors = when {
        isUseMaterialYou && isAndroidS && darkTheme -> dynamicLightColorScheme(context)
        isUseMaterialYou && isAndroidS -> dynamicLightColorScheme(context)
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}