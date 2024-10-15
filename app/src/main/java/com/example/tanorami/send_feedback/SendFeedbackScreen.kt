package com.example.tanorami.send_feedback

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseDefaultText
import com.example.tanorami.base_components.BaseSmallFloatingButton
import com.example.tanorami.base_components.BaseTopAppBar
import com.example.tanorami.core.theme.Grey
import com.example.tanorami.send_feedback.presentation.SendFeedbackViewModel
import com.example.tanorami.send_feedback.presentation.models.SendFeedbackScreenEvents
import com.example.tanorami.send_feedback.presentation.models.SendFeedbackScreenSideEffects
import com.example.tanorami.send_feedback.presentation.models.SendFeedbackScreenUiState
import com.example.tanorami.utils.toast

@Composable
fun SendFeedbackScreen(
    viewModel: SendFeedbackViewModel,
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value
    val context = LocalContext.current

    SendFeedbackScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when(sideEffects) {
        SendFeedbackScreenSideEffects.OnBack -> navController.popBackStack()
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
            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = uiState.messageValue,
                onValueChange = { newValue -> onEvent(SendFeedbackScreenEvents.MessageChanged(newValue)) },
                enabled = !uiState.isSendingFeedback,
                label = {
                    BaseDefaultText(text = stringResource(id = R.string.message))
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Feedback, contentDescription = null)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedLabelColor = Grey,
                    focusedLabelColor = MaterialTheme.colorScheme.secondary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    focusedBorderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
                    focusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
                    cursorColor = MaterialTheme.colorScheme.secondary,
                )
            )
        }
    }
}