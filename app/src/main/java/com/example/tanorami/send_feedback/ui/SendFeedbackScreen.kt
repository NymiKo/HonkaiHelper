package com.example.tanorami.send_feedback.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.core.ui.base_components.button.BaseSmallFloatingButton
import com.example.tanorami.core.ui.base_components.text_field.BaseOutlinedTextField
import com.example.tanorami.core.ui.base_components.top_app_bar.BaseTopAppBar
import com.example.tanorami.send_feedback.presentation.SendFeedbackViewModel
import com.example.tanorami.send_feedback.presentation.models.SendFeedbackScreenEvents
import com.example.tanorami.send_feedback.presentation.models.SendFeedbackScreenSideEffects
import com.example.tanorami.send_feedback.presentation.models.SendFeedbackScreenUiState
import com.example.tanorami.utils.toast
import kotlinx.serialization.Serializable

@Serializable
data object SendFeedbackRoute

@Composable
fun SendFeedbackScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: SendFeedbackViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value
    val context = LocalContext.current

    SendFeedbackScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        SendFeedbackScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }
        is SendFeedbackScreenSideEffects.ShowToast -> {
            toast(context, sideEffects.message)
            viewModel.clearEffect()
        }

        null -> {}
    }
}

@Composable
private fun SendFeedbackScreenContent(
    uiState: SendFeedbackScreenUiState,
    onEvent: (SendFeedbackScreenEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            BaseTopAppBar(
                title = stringResource(id = R.string.report_an_error),
                onBack = { onEvent(SendFeedbackScreenEvents.OnBack) },
            )
        },
        floatingActionButton = {
            BaseSmallFloatingButton(
                icon = Icons.AutoMirrored.Filled.Send,
                onClick = {
                    onEvent(SendFeedbackScreenEvents.SendFeedbackClick)
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            BaseOutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = uiState.messageValue,
                onValueChanged = { newValue ->
                    onEvent(
                        SendFeedbackScreenEvents.MessageChanged(
                            newValue
                        )
                    )
                },
                enabled = !uiState.isSendingFeedback,
                label = stringResource(id = R.string.message)
            )
        }
    }
}