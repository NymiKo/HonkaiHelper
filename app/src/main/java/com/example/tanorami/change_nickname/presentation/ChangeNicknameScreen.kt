package com.example.tanorami.change_nickname.presentation

import androidx.compose.foundation.background
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
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseTopAppBar
import com.example.tanorami.core.theme.Red
import kotlinx.coroutines.launch

@Composable
fun ChangeNicknameScreen(
    modifier: Modifier = Modifier,
    viewModel: ChangeNicknameViewModel,
    onBack: () -> Unit
) {
    ChangeNicknameScreenContent(
        modifier = modifier,
        uiState = viewModel.uiState,
        onEvent = { event ->
            when (event) {
                ChangeNicknameScreenEvents.OnBack -> onBack()
                else -> Unit
            }
            viewModel.onEvent(event)
        },
    )
}

@Composable
private fun ChangeNicknameScreenContent(
    modifier: Modifier = Modifier,
    uiState: ChangeNicknameScreenUiState,
    onEvent: (ChangeNicknameScreenEvents) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = uiState.success) {
        if (uiState.success) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = "Никнейм изменен",
                    withDismissAction = true
                )
            }
        }
    }

    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
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
            enteringNickname = { onEvent(ChangeNicknameScreenEvents.EnteringNickname(it)) }
        )
    }
}

@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {
    BaseTopAppBar(
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
    enteringNickname: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        value = uiState.newNickname,
        onValueChange = { enteringNickname(it) },
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
        isError = uiState.error,
        supportingText = {
            if (uiState.error) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = uiState.errorMessage),
                    color = Red
                )
            }
        },
        enabled = !uiState.loading
    )
}

@Preview
@Composable
fun ChangeNicknameScreenPreview(modifier: Modifier = Modifier) {
    ChangeNicknameScreenContent(uiState = ChangeNicknameScreenUiState(), onEvent = {})
}