package com.chobitech.lib.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver


@Composable
fun OnLifecycleEvent(onEvent: (event: Lifecycle.Event) -> Unit) {
    val eventHandler by rememberUpdatedState(onEvent)
    val lifeCycleOwner by rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifeCycleOwner) {
        val lifeCycle = lifeCycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, ev ->
            eventHandler(ev)
        }

        lifeCycle.addObserver(observer)

        onDispose {
            lifeCycle.removeObserver(observer)
        }
    }
}
