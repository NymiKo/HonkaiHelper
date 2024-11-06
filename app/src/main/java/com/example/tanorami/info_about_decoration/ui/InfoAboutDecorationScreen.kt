package com.example.tanorami.info_about_decoration.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tanorami.base_components.top_app_bar.BaseCenterAlignedTopAppBar
import com.example.tanorami.core.navigation.LocalNavAnimatedVisibilityScope
import com.example.tanorami.core.navigation.LocalSharedTransitionScope
import com.example.tanorami.info_about_decoration.presentation.InfoAboutDecorationViewModel
import com.example.tanorami.info_about_decoration.ui.components.DescriptionDecorationEffect
import com.example.tanorami.info_about_hero.data.model.Decoration
import com.example.tanorami.utils.OnLifecycleEvent
import kotlinx.serialization.Serializable

@Serializable
data class InfoAboutDecorationNavArguments(val idDecoration: Int)

@Composable
internal fun InfoAboutDecorationScreen(
    navArguments: InfoAboutDecorationNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: InfoAboutDecorationViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val decoration = viewModel.decoration.collectAsStateWithLifecycle().value

    InfoAboutDecorationScreenContent(
        decoration = decoration,
        onBack = navController::popBackStack
    )

    OnLifecycleEvent { owner, event ->
        when(event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.getDecoration(navArguments.idDecoration)
            }

            else -> {}
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun InfoAboutDecorationScreenContent(
    decoration: Decoration?,
    onBack: () -> Unit,
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No AnimatedVisibility found")

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
            with(sharedTransitionScope) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .sharedElement(
                            rememberSharedContentState(key = "decoration-${decoration?.idDecoration}-base-build"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    model = decoration?.image,
                    contentDescription = null
                )
            }

            DescriptionDecorationEffect(descriptionDecorationEffect = decoration?.description)
        }
    }
}