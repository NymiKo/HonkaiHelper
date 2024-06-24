package com.example.tanorami.change_nickname

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseTopAppBar
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

    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            BaseTopAppBar(
                title = stringResource(id = R.string.nickname_change)
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary,
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    onEvent(ChangeNicknameScreenEvents.ChangeNickname)

                    if (uiState.success) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Никнейм изменен",
                                withDismissAction = true
                            )
                        }
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "")
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.newNickname,
                onValueChange = { onEvent(ChangeNicknameScreenEvents.EnteringNickname(it)) },
                label = { Text(text = stringResource(id = R.string.new_nickname), color = MaterialTheme.colorScheme.secondary) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    cursorColor = MaterialTheme.colorScheme.secondary,
                ),
                isError = uiState.error,
                supportingText = {
                    if (uiState.error) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = uiState.errorMessage),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
        }
    }
}