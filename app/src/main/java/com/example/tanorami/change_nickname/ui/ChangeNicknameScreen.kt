package com.example.tanorami.change_nickname.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.ui.theme.Red
import com.example.strings.R
import com.example.tanorami.change_nickname.presentation.ChangeNicknameViewModel
import com.example.tanorami.change_nickname.presentation.models.ChangeNicknameScreenEvents
import com.example.tanorami.change_nickname.presentation.models.ChangeNicknameScreenUiState
import com.example.tanorami.utils.OnLifecycleEvent
import com.example.ui.theme.AppTheme
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class ChangeNicknameNavArguments(val nickname: String)

@Composable
fun ChangeNicknameScreen(
    navArguments: ChangeNicknameNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: ChangeNicknameViewModel = viewModel(factory = viewModelFactory),
    onBack: () -> Unit
) {
    ChangeNicknameScreenContent(
        uiState = viewModel.uiState,
        onEvent = { event ->
            when (event) {
                ChangeNicknameScreenEvents.OnBack -> {
                    onBack()
                }
                else -> Unit
            }
            viewModel.onEvent(event)
        },
    )

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> viewModel.onEvent(
                ChangeNicknameScreenEvents.SetOldNickname(
                    navArguments.nickname
                )
            )

            else -> {}
        }
    }
}

@Composable
private fun ChangeNicknameScreenContent(
    uiState: ChangeNicknameScreenUiState,
    onEvent: (ChangeNicknameScreenEvents) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = uiState.isSuccess) {
        if (uiState.isSuccess || uiState.isError) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Никнейм изменен",
                    withDismissAction = true
                )
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(onBack = { onEvent(ChangeNicknameScreenEvents.OnBack) })
        },
        snackbarHost = {
            SnackbarHostWithSnackbar(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ChangeNicknameButton(changeNickname = { onEvent(ChangeNicknameScreenEvents.ChangeNickname) })
        },
    ) { innerPadding ->
        EditNicknameField(
            modifier = Modifier.padding(innerPadding),
            uiState = uiState,
            nicknameChanged = { onEvent(ChangeNicknameScreenEvents.NicknameChanged(it)) }
        )
    }
}

@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    com.example.ui.components.top_app_bar.BaseTopAppBar(
        modifier = modifier,
        title = stringResource(id = R.string.nickname_change),
        onBack = { onBack() },
    )
}

@Composable
fun SnackbarHostWithSnackbar(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState,
) {
    SnackbarHost(modifier = modifier, hostState = hostState) { data ->
        Snackbar(
            snackbarData = data,
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
fun ChangeNicknameButton(
    modifier: Modifier = Modifier,
    changeNickname: () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.primary,
        onClick = { changeNickname() }
    ) {
        Icon(imageVector = Icons.Default.Save, contentDescription = "")
    }
}

@Composable
private fun EditNicknameField(
    modifier: Modifier = Modifier,
    uiState: ChangeNicknameScreenUiState,
    nicknameChanged: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        value = uiState.newNickname.value,
        onValueChange = { nicknameChanged(it) },
        label = {
            Text(
                text = stringResource(id = R.string.new_nickname),
                color = MaterialTheme.colorScheme.secondary
            )
        },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary,
            errorBorderColor = Red,
        ),
        isError = uiState.newNickname.isError,
        supportingText = {
            if (uiState.newNickname.isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = uiState.newNickname.errorMessage),
                    color = Red
                )
            }
        },
        enabled = !uiState.isLoading
    )
}

@Preview
@Composable
private fun ChangeNicknameScreenPreview() {
    AppTheme {
        ChangeNicknameScreenContent(uiState = ChangeNicknameScreenUiState(), onEvent = {})
    }
}