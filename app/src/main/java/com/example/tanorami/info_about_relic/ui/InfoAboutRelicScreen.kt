package com.example.tanorami.info_about_relic.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.common.RelicModel
import com.example.tanorami.info_about_relic.presentation.InfoAboutRelicViewModel
import com.example.tanorami.info_about_relic.ui.components.DescriptionRelicEffect
import com.example.tanorami.info_about_relic.ui.components.ImageRelic
import com.example.tanorami.utils.OnLifecycleEvent
import kotlinx.serialization.Serializable

@Serializable
data class InfoAboutRelicNavArgument(val idRelic: Int)

@Composable
fun InfoAboutRelicScreen(
    navArguments: InfoAboutRelicNavArgument,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: InfoAboutRelicViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val relic = viewModel.relic.collectAsStateWithLifecycle().value

    InfoAboutRelicScreenContent(
        relic = relic,
        onBack = navController::popBackStack
    )

    OnLifecycleEvent { owner, event ->
        when(event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.getRelic(navArguments.idRelic)
            }

            else -> {}
        }
    }
}

@Composable
internal fun InfoAboutRelicScreenContent(
    relic: RelicModel?,
    onBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            com.example.ui.components.top_app_bar.BaseCenterAlignedTopAppBar(
                title = relic?.title,
                onBack = onBack::invoke
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ImageRelic(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                idRelic = relic?.idRelic,
                imageRelic = relic?.image
            )

            DescriptionRelicEffect(
                descriptionTwoRelicEffect = relic?.descriptionTwoParts,
                descriptionFourRelicEffect = relic?.descriptionFourParts
            )
        }
    }
}