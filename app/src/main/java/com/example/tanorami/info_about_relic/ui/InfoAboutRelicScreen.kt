package com.example.tanorami.info_about_relic.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.tanorami.base_components.top_app_bar.BaseCenterAlignedTopAppBar
import com.example.tanorami.info_about_hero.data.model.Relic
import com.example.tanorami.info_about_relic.presentation.RelicInfoViewModel
import com.example.tanorami.info_about_relic.ui.components.DescriptionRelicEffect
import com.example.tanorami.info_about_relic.ui.components.ImageRelic
import com.example.tanorami.utils.OnLifecycleEvent

@Composable
fun InfoAboutRelicScreen(
    idRelic: Int,
    viewModel: RelicInfoViewModel,
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
                viewModel.getRelic(idRelic)
            }

            else -> {}
        }
    }
}

@Composable
internal fun InfoAboutRelicScreenContent(
    relic: Relic?,
    onBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BaseCenterAlignedTopAppBar(
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
            ImageRelic(imageRelic = relic?.image)

            DescriptionRelicEffect(
                descriptionTwoRelicEffect = relic?.descriptionTwoParts,
                descriptionFourRelicEffect = relic?.descriptionFourParts
            )
        }
    }
}