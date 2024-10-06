package com.example.tanorami.info_about_decoration.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tanorami.base_components.BaseCenterAlignedTopAppBar
import com.example.tanorami.info_about_decoration.presentation.InfoAboutDecorationViewModel
import com.example.tanorami.info_about_decoration.ui.components.DescriptionDecorationEffect
import com.example.tanorami.info_about_hero.data.model.Decoration
import com.example.tanorami.utils.OnLifecycleEvent

@Composable
internal fun InfoAboutDecorationScreen(
    idDecoration: Int,
    viewModel: InfoAboutDecorationViewModel,
    navController: NavController,
) {
    val decoration = viewModel.decoration.collectAsStateWithLifecycle().value

    InfoAboutDecorationScreenContent(
        decoration = decoration,
        onBack = navController::popBackStack
    )

    OnLifecycleEvent { owner, event ->
        when(event) {
            Lifecycle.Event.ON_START -> {
                viewModel.getDecoration(idDecoration)
            }

            else -> {}
        }
    }
}

@Composable
private fun InfoAboutDecorationScreenContent(
    decoration: Decoration?,
    onBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BaseCenterAlignedTopAppBar(
                title = decoration?.title,
                onBack = onBack::invoke
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                model = decoration?.image,
                contentDescription = null
            )

            DescriptionDecorationEffect(descriptionDecorationEffect = decoration?.description)
        }
    }
}