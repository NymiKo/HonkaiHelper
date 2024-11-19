package com.example.tanorami.auth.login.ui

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.R.drawable
import com.example.core.ui.theme.Grey
import com.example.strings.R
import com.example.tanorami.auth.login.presentation.LoginViewModel
import com.example.tanorami.auth.login.presentation.models.LoginScreenEvents
import com.example.tanorami.auth.login.presentation.models.LoginScreenSideEffects
import com.example.tanorami.auth.login.presentation.models.LoginScreenUiState
import com.example.tanorami.auth.login.ui.components.LoginButton
import com.example.tanorami.auth.login.ui.components.LoginOutlinedTextField
import com.example.tanorami.auth.registration.ui.RegistrationRoute
import com.example.tanorami.utils.toast
import com.example.ui.components.text.BaseDefaultText
import com.example.ui.components.top_app_bar.BaseCenterAlignedTopAppBar
import kotlinx.serialization.Serializable

@Serializable
data object LoginRoute

@Composable
fun LoginScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: LoginViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value
    val context = LocalContext.current

    LoginScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        LoginScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }

        LoginScreenSideEffects.OnRegistrationScreen -> {
            navController.navigate(route = RegistrationRoute)
            viewModel.clearEffect()
        }

        is LoginScreenSideEffects.ShowToast -> {
            toast(context, sideEffects.message)
            viewModel.clearEffect()
        }

        null -> {}
    }
}

@Composable
private fun LoginScreenContent(
    uiState: LoginScreenUiState,
    onEvent: (LoginScreenEvents) -> Unit,
) {
    Scaffold(
        topBar = {
            BaseCenterAlignedTopAppBar(
                title = stringResource(id = R.string.log_in_account),
                onBack = { onEvent(LoginScreenEvents.OnBack) }
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
                text = stringResource(id = R.string.welcome),
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
            )

            AsyncImage(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .size(130.dp),
                model = drawable.pom_pom_hey,
                contentDescription = null
            )

            LoginOutlinedTextField(
                value = uiState.nicknameField.value,
                onValueChanged = { newValue -> onEvent(LoginScreenEvents.LoginChanged(newValue)) },
                label = stringResource(id = R.string.nickname),
                enabled = !uiState.isAuthentication,
                isError = uiState.nicknameField.isError,
                supportingText = stringResource(id = uiState.nicknameField.errorMessage)
            )

            LoginOutlinedTextField(
                value = uiState.passwordField.value,
                onValueChanged = { newValue -> onEvent(LoginScreenEvents.PasswordChanged(newValue)) },
                label = stringResource(id = R.string.password),
                enabled = !uiState.isAuthentication,
                isError = uiState.passwordField.isError,
                supportingText = stringResource(id = uiState.passwordField.errorMessage),
                visualTransformation = if (uiState.passwordField.visualTransformation) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    IconButton(onClick = {
                        onEvent(LoginScreenEvents.PasswordVisibilityChange)
                    }) {
                        Icon(
                            imageVector = if (uiState.passwordField.visualTransformation) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                }
            )

            LoginButton(
                isProgress = uiState.isAuthentication,
                text = stringResource(id = R.string.log_in),
                onClick = { onEvent(LoginScreenEvents.Authentication) }
            )

            BaseDefaultText(
                modifier = Modifier.clickable { onEvent(LoginScreenEvents.OnRegistrationScreen) },
                text = stringResource(id = R.string.create_account),
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                color = Grey,
            )
        }
    }
}