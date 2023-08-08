package com.chobitech.lib.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

open class ModeObject<T>(val lightModeValue: T, val darkModeValue: T) {

    fun getValue(isDarkMode: Boolean) = when (isDarkMode) {
        true -> darkModeValue
        false -> lightModeValue
    }

    @Composable
    fun rememberModeObjectValue(isDarkMode: Boolean = isSystemInDarkTheme()): T {
        return remember(isDarkMode) {
            getValue(isDarkMode)
        }
    }

}
