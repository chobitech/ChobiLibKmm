package com.chobitech.lib.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.math.min

fun LifecycleOwner.createLifecycleScopeFunc(dispatcher: CoroutineDispatcher? = null, proc: suspend () -> Unit): () -> Unit {
    when (dispatcher) {
        null -> return {
            lifecycleScope.launch {
                proc()
            }
        }
        else -> return {
            lifecycleScope.launch(dispatcher) {
                proc()
            }
        }
    }
}

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifeCycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifeCycleOwner.value) {
        val lifecycle = lifeCycleOwner.value.lifecycle

        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}


fun CoroutineScope.createLaunchFunc(cContext: CoroutineContext? = null, proc: suspend () -> Unit): () -> Unit {
    if (cContext == null) {
        return {
            this.launch {
                proc()
            }
        }
    } else {
        return {
            this.launch(cContext) {
                proc()
            }
        }
    }
}

@Composable
fun sizeByDp(width: Dp, height: Dp): Size {
    return with(LocalDensity.current) {
        Size(width.toPx(), height.toPx())
    }
}

@Composable
fun floatByDp(dp: Dp): Float {
    return with(LocalDensity.current) {
        dp.toPx()
    }
}

val Size.aspect: Float
    get() = width / height

fun Size.insideIn(bounds: Size): Size {
    val selfAspect = aspect

    var newWidth = min(width, bounds.width)
    var newHeight = newWidth / selfAspect

    if (newHeight > bounds.height) {
        newHeight = bounds.height
        newWidth = newHeight * selfAspect
    }

    return Size(newWidth, newHeight)
}

fun Size.fillIn(bounds: Size): Size {
    val selfAspect = aspect
    val boundsAspect = bounds.aspect

    val newWidth: Float
    val newHeight: Float

    when (selfAspect >= boundsAspect) {
        true -> {
            newHeight = bounds.height
            newWidth = newHeight * selfAspect
        }
        false -> {
            newWidth = bounds.width
            newHeight = newWidth / selfAspect
        }
    }

    return Size(newWidth, newHeight)
}
