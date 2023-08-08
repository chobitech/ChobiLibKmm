package com.chobitech.lib

import androidx.compose.ui.graphics.Color
import com.chobitech.lib.kmm.ChobiColor


fun <T> List<T>.merge(vararg lists: List<T>): List<T> {
    return lists.flatMap { it }
}

fun ChobiColor.toComposableColor(): Color {
    return Color(
        red = red,
        green = green,
        blue = blue,
        alpha = alpha
    )
}
