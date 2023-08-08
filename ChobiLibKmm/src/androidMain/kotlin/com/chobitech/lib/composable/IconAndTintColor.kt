package com.chobitech.lib.composable

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class IconAndTintColor(
    val icon: ImageVector,
    val tintColor: Color? = null
)
