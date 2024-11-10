package com.example.tanorami.auth.registration.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.auth.login.ui.components.LoginButton
import com.example.tanorami.auth.login.ui.components.LoginOutlinedTextField
import com.example.tanorami.auth.registration.presentation.RegistrationViewModel
import com.example.tanorami.auth.registration.presentation.models.RegistrationScreenEvent
import com.example.tanorami.auth.registration.presentation.models.RegistrationScreenSideEffects
import com.example.tanorami.auth.registration.presentation.models.RegistrationScreenUiState
import com.example.tanorami.core.ui.base_components.text.BaseDefaultText
import com.example.tanorami.core.ui.base_components.top_app_bar.BaseCenterAlignedTopAppBar
import com.example.tanorami.utils.toast
import kotlinx.serialization.Serializable

@Serializable
data object RegistrationRoute

@Composable
fun RegistrationScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: RegistrationViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value
    val context = LocalContext.current

    RegistrationScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        RegistrationScreenSideEffects.SuccessCreatingAccount -> {
            toast(context, R.string.success_creating_account)
            navController.popBackStack()
            viewModel.clearEffect()
        }

        is RegistrationScreenSideEffects.ShowToast -> {
            toast(context, sideEffects.message)
            viewModel.clearEffect()
        }

        RegistrationScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }
        null -> {}
    }
}

@Composable
private fun RegistrationScreenContent(
    uiState: RegistrationScreenUiState,
    onEvent: (RegistrationScreenEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            BaseCenterAlignedTopAppBar(
                title = stringResource(id = R.string.creating_account),
                onBack = { onEvent(RegistrationScreenEvent.OnBack) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BaseDefaultText(
                text = stringResource(id = R.string.join_us),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
            )

            AsyncImage(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .size(130.dp),
                model = R.drawable.pom_pom_heart,
                contentDescription = null
            )

            LoginOutlinedTextField(
                value = uiState.nicknameField.value,
                onValueChanged = { newValue -> onEvent(RegistrationScreenEvent.LoginChanged(newValue)) },
                label = stringResource(id = R.string.nickname),
                enabled = !uiState.isCreatingAccount,
                isError = uiState.nicknameField.isError,
                supportingText = stringResource(id = uiState.nicknameField.errorMessage)
            )

            LoginOutlinedTextField(
                value = uiState.passwordField.value,
                onValueChanged = { newValue ->
                    onEvent(
                        RegistrationScreenEvent.PasswordChanged(
                            newValue
                        )
                    )
                },
                label = stringResource(id = R.string.password),
                enabled = !uiState.isCreatingAccount,
                isError = uiState.passwordField.isError,
                supportingText = stringResource(id = uiState.passwordField.errorMessage),
                visualTransformation = if (uiState.passwordField.visualTransformation) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = {
                        onEvent(RegistrationScreenEvent.PasswordVisibilityChange)
                    }) {
                        Icon(
                            imageVector = if (uiState.passwordField.visualTransformation) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                }
            )

            LoginButton(
                isProgress = uiState.isCreatingAccount,
                text = stringResource(id = R.string.create_account),
                onClick = { onEvent(RegistrationScreenEvent.CreateAccount) }
            )
        }
    }
}