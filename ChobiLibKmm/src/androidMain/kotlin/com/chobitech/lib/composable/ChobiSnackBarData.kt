package com.chobitech.lib.composable

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember


data class SnackBarActionData(
    val label: String,
    val action: () -> Unit
)

data class ChobiSnackBarData(
    val message: String,
    val key: Long = System.currentTimeMillis(),
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val iconAndTintColor: IconAndTintColor? = null,
    val onDismiss: (() -> Unit)? = null,
    val actionData: SnackBarActionData? = null
)

@Composable
fun LaunchShowSnackBar(
    snackbarHostState: SnackbarHostState,
    dataProvider: () -> ChobiSnackBarData?
) {
    val data by remember(dataProvider) {
        derivedStateOf {
            dataProvider()
        }
    }

    LaunchedEffect(data) {
        data?.also { csData ->
            snackbarHostState.showSnackbar(
                message = csData.message,
                actionLabel = csData.actionData?.label,
                withDismissAction = true,
                duration = csData.duration
            ).also { result ->
                csData.actionData?.action?.also { act ->
                    if (result == SnackbarResult.ActionPerformed) {
                        act()
                    }
                }
                csData.onDismiss?.invoke()
            }
        }
    }
}
