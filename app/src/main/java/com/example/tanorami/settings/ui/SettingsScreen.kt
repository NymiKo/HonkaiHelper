package com.example.tanorami.settings.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.ui.theme.DarkGrey
import com.example.strings.R
import com.example.tanorami.R.*
import com.example.tanorami.load_data.ui.LoadDataNavArguments
import com.example.tanorami.send_feedback.ui.SendFeedbackRoute
import com.example.tanorami.settings.presentation.SettingsViewModel
import com.example.tanorami.settings.presentation.models.SettingsScreenEvents
import com.example.tanorami.settings.presentation.models.SettingsScreenSideEffects
import com.example.tanorami.settings.presentation.models.SettingsScreenUiState
import com.example.tanorami.utils.toast
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute

@Composable
fun SettingsScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: SettingsViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffect = viewModel.uiEffect().collectAsState(initial = null).value
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current

    SettingsScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when(sideEffect) {
        SettingsScreenSideEffects.OnSendFeedbackScreen -> {
            navController.navigate(route = SendFeedbackRoute)
            viewModel.clearEffect()
        }

        is SettingsScreenSideEffects.OnLoadDataScreen -> {
            navController.navigate(route = LoadDataNavArguments(remoteVersionDB = sideEffect.versionDB))
            viewModel.clearEffect()
        }

        is SettingsScreenSideEffects.ShowToast -> {
            toast(context, sideEffect.message)
            viewModel.clearEffect()
        }

        SettingsScreenSideEffects.CLickDonateButton -> {
            uriHandler.openUri("https://www.donationalerts.com/r/nymiko")
            viewModel.clearEffect()
        }

        SettingsScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }

        null -> {}
    }
}

@Composable
private fun SettingsScreenContent(
    uiState: SettingsScreenUiState,
    onEvent: (SettingsScreenEvents) -> Unit
) {
    Scaffold(
        topBar = {
            com.example.ui.components.top_app_bar.BaseTopAppBar(
                title = stringResource(id = R.string.about_app),
                onBack = { onEvent(SettingsScreenEvents.OnBack) },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .border(1.dp, DarkGrey, RoundedCornerShape(10.dp))
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.size(50.dp),
                        model = mipmap.ic_launcher_round,
                        contentDescription = null
                    )

                    Column {
                        com.example.ui.components.text.BaseDefaultText(
                            text = stringResource(id = R.string.app_name),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        com.example.ui.components.text.BaseDefaultText(
                            text = stringResource(id = R.string.version_app),
                            fontSize = 16.sp,
                        )

                        com.example.ui.components.text.BaseDefaultText(
                            text = stringResource(id = R.string.version_db, uiState.versionDB),
                            fontSize = 16.sp,
                        )
                    }
                }

                com.example.ui.components.text.BaseDefaultText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.about_developers),
                    fontSize = 16.sp,
                    lineHeight = 18.sp
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .size(30.dp),
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    } else {
                        com.example.ui.components.button.BaseButton(
                            modifier = Modifier.padding(top = 16.dp),
                            fontSize = 14.sp,
                            text = stringResource(id = R.string.check_update_db),
                            onClick = { onEvent(SettingsScreenEvents.CheckUpdate) }
                        )
                    }
                }
            }

            com.example.ui.components.button.BaseButton(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(id = R.string.report_an_error),
                fontSize = 16.sp,
                onClick = { onEvent(SettingsScreenEvents.OnSendFeedbackScreen) }
            )

            Spacer(modifier = Modifier.weight(1F))

            com.example.ui.components.text.BaseDefaultText(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.thank_the_developer),
                fontSize = 12.sp,
            )

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable {
                        onEvent(SettingsScreenEvents.CLickDonateButton)
                    },
                model = R.drawable.donate,
                contentDescription = null,
            )
        }
    }
}