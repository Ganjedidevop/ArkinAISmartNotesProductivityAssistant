package com.example.arkin_aismartnotesproductivityassistant.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily

val ArkinBg: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF121212) else Color(0xFFF8F9FF)
val ArkinPrimary = Color(0xFF5D5FEF)
val ArkinPrimaryHover = Color(0xFF4B4CE0)
val ArkinText: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF1E1E2D)
val ArkinTextMuted: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFFA0A0B0) else Color(0xFF8F90A6)
val ArkinCard: Color
    @Composable get() = if (isSystemInDarkTheme()) Color(0xFF1E1E2D) else Color(0xFFFFFFFF)

val IconBgPurple = Color(0xFFF3E8FF)
val IconColorPurple = Color(0xFF9333EA)
val IconBgTeal = Color(0xFFE0F2FE)
val IconColorTeal = Color(0xFF0284C7)
val IconBgOrange = Color(0xFFFFEDD5)
val IconColorOrange = Color(0xFFEA580C)
val IconBgBlue = Color(0xFFE0E7FF)
val IconColorBlue = Color(0xFF4F46E5)
val IconBgPink = Color(0xFFFCE7F3)
val IconColorPink = Color(0xFFDB2777)
val IconBgLocker = Color(0xFFE0F2FE)
val IconColorLocker = Color(0xFF2563EB)
val IconBgYellow = Color(0xFFFEF9C3)
val IconColorYellow = Color(0xFFCA8A04)

@Composable
fun ArkinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme(
        primary = ArkinPrimary,
        background = ArkinBg,
        surface = ArkinCard,
        onPrimary = Color.White,
        onBackground = ArkinText,
        onSurface = ArkinText
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}
