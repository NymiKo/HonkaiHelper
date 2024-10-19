package com.example.tanorami.load_data.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.tanorami.load_data.presentation.LoadDataViewModel
import com.example.tanorami.load_data.presentation.models.LoadDataScreenEvents
import com.example.tanorami.load_data.presentation.models.LoadDataScreenSideEffects
import com.example.tanorami.load_data.presentation.models.LoadDataScreenUiState
import com.example.tanorami.load_data.ui.components.ErrorUploadingDataComponent
import com.example.tanorami.load_data.ui.components.UploadingDataComponent
import com.example.tanorami.utils.OnLifecycleEvent
import com.example.tanorami.utils.popBackStackWithResult

@Composable
fun LoadDataScreen(
    newVersionDB: String,
    viewModel: LoadDataViewModel,
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value

    LoadDataScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        LoadDataScreenSideEffects.OnBack -> {
            navController.popBackStackWithResult(DATA_UPLOADED_KEY, true)
        }

        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> viewModel.onEvent(
                LoadDataScreenEvents.UploadData(
                    newVersionDB
                )
            )

            else -> {}
        }
    }
}

@Composable
private fun LoadDataScreenContent(
    uiState: LoadDataScreenUiState,
    onEvent: (LoadDataScreenEvents) -> Unit,
) {
    when {
        uiState.isUploadingData -> UploadingDataComponent()
        uiState.isErrorDataUpload -> {
            ErrorUploadingDataComponent(
                onRetryClick = { onEvent(LoadDataScreenEvents.UploadData(uiState.newVersionDB)) },
                onBack = { onEvent(LoadDataScreenEvents.OnBack) }
            )
        }
    }
}