package com.chobitech.lib.android.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dp.getPxWithDensity(): Float {
    return with (LocalDensity.current) {
        toPx()
    }
}

val dp0 = 0.dp
val dp1 = 1.dp
val dp8 = 8.dp
val dp16 = 16.dp
val dp24 = 24.dp
val dp32 = 32.dp
val dp48 = 48.dp
val dp56 = 56.dp
val dp72 = 72.dp
