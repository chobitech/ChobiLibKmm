package com.chobitech.lib.android.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

open class ModeColor(lightModeColor: Color, darkModeColor: Color) : ModeObject<Color>(lightModeColor, darkModeColor) {

    companion object {

        @Composable
        fun ModeTextStyle(
            modeColor: ModeColor,
            isDarkMode: Boolean = isSystemInDarkTheme(),
            content: @Composable () -> Unit
        ) {
            val textStyle = remember(isDarkMode) {
                TextStyle(color = modeColor.getColor(isDarkMode))
            }

            ProvideTextStyle(
                value = textStyle,
                content = content
            )
        }

    }

    fun getColor(isDarkMode: Boolean): Color = getValue(isDarkMode)

    @Composable
    fun rememberModeColor(isDarkMode: Boolean = isSystemInDarkTheme()): Color {
        return remember(isDarkMode) {
            getColor(isDarkMode)
        }
    }

    @Composable
    fun ModeTextStyle(
        isDarkMode: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        Companion.ModeTextStyle(
            modeColor = this,
            isDarkMode = isDarkMode,
            content = content
        )
    }
}
