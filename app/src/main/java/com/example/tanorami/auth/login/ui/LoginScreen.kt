package com.example.tanorami.auth.login.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.auth.login.presentation.LoginViewModel
import com.example.tanorami.auth.login.presentation.models.LoginScreenEvents
import com.example.tanorami.auth.login.presentation.models.LoginScreenSideEffects
import com.example.tanorami.auth.login.presentation.models.LoginScreenUiState
import com.example.tanorami.base_components.button.BaseButton
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.base_components.text_field.BaseOutlinedTextField
import com.example.tanorami.base_components.top_app_bar.BaseCenterAlignedTopAppBar
import com.example.tanorami.core.theme.Grey
import com.example.tanorami.utils.toast

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
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
        LoginScreenSideEffects.OnBack -> navController.popBackStack()
        LoginScreenSideEffects.OnRegistrationScreen -> {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
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
                model = R.drawable.pom_pom_hey,
                contentDescription = null
            )

            BaseOutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = uiState.loginField.value,
                onValueChanged = { newValue -> onEvent(LoginScreenEvents.LoginChanged(newValue)) },
                label = stringResource(id = R.string.nickname),
                enabled = !uiState.isAuthentication,
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                isError = uiState.loginField.isError,
                supportingText = stringResource(id = uiState.loginField.errorMessage),
            )

            BaseOutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                value = uiState.passwordField.value,
                onValueChanged = { newValue -> onEvent(LoginScreenEvents.PasswordChanged(newValue)) },
                label = stringResource(id = R.string.password),
                enabled = !uiState.isAuthentication,
                shape = RoundedCornerShape(10.dp),
                singleLine = true,
                isError = uiState.passwordField.isError,
                supportingText = stringResource(id = uiState.passwordField.errorMessage)
            )

            if (uiState.isAuthentication) {
                CircularProgressIndicator(
                    modifier = Modifier.size(45.dp),
                    color = MaterialTheme.colorScheme.secondary,
                )
            } else {
                BaseButton(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .height(45.dp),
                    text = stringResource(id = R.string.log_in),
                    onClick = { onEvent(LoginScreenEvents.Authentication) }
                )
            }

            BaseDefaultText(
                modifier = Modifier.clickable { onEvent(LoginScreenEvents.OnRegistrationScreen) },
                text = stringResource(id = R.string.registration),
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                color = Grey,
            )
        }
    }
}