package com.example.tanorami.info_about_weapon.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tanorami.info_about_hero.ui.InfoAboutHeroNavArguments
import com.example.tanorami.info_about_weapon.presentation.InfoAboutWeaponViewModel
import com.example.tanorami.info_about_weapon.presentation.models.InfoAboutWeaponEvents
import com.example.tanorami.info_about_weapon.presentation.models.InfoAboutWeaponUiSideEffects
import com.example.tanorami.info_about_weapon.presentation.models.InfoAboutWeaponUiState
import com.example.tanorami.info_about_weapon.ui.components.DescriptionWeaponSkill
import com.example.tanorami.info_about_weapon.ui.components.ImageRarityWeapon
import com.example.tanorami.info_about_weapon.ui.components.ImagesWeaponAndPath
import com.example.tanorami.utils.OnLifecycleEvent
import com.example.ui.components.hero.BaseHeroAvatarAndName
import com.example.ui.components.text.BaseDefaultText
import com.example.ui.components.top_app_bar.BaseCenterAlignedTopAppBar
import kotlinx.serialization.Serializable

@Serializable
data class InfoAboutWeaponNavArguments(val idWeapon: Int)

@Composable
fun InfoAboutWeaponScreen(
    navArguments: InfoAboutWeaponNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: InfoAboutWeaponViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val uiState = viewModel.uiState().collectAsStateWithLifecycle().value
    val sideEffects = viewModel.uiEffect().collectAsState(null).value

    when (sideEffects) {
        is InfoAboutWeaponUiSideEffects.OnInfoAboutHeroScreen -> {
            navController.navigate(InfoAboutHeroNavArguments(idHero = sideEffects.idHero))
            viewModel.clearEffect()
        }

        InfoAboutWeaponUiSideEffects.OnBackClick -> {
            navController.popBackStack()
        }

        null -> {}
    }

    InfoAboutWeaponScreenContent(
        uiState = uiState,
        onEvents = viewModel::onEvent,
    )
    
    OnLifecycleEvent { owner, event ->  
        when(event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(InfoAboutWeaponEvents.GetWeaponByID(navArguments.idWeapon))
            }
            
            else -> {}
        }
    }
}

@Composable
private fun InfoAboutWeaponScreenContent(
    uiState: InfoAboutWeaponUiState?,
    onEvents: (InfoAboutWeaponEvents) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BaseCenterAlignedTopAppBar(
                title = uiState?.weaponInfo?.weapon?.name ?: "",
                onBack = { onEvents(InfoAboutWeaponEvents.OnBackClick) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(bottom = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ImagesWeaponAndPath(
                weapon = uiState?.weaponInfo?.weapon,
            )

            ImageRarityWeapon(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                rarity = uiState?.weaponInfo?.weapon?.rarity
            )

            LazyRow(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(items = uiState?.weaponInfo?.heroesList ?: emptyList()) { hero ->
                    BaseHeroAvatarAndName(
                        modifier = Modifier.clickable {
                            onEvents(InfoAboutWeaponEvents.OnHeroClick(hero.id))
                        },
                        hero = hero
                    )
                }
            }

            DescriptionWeaponSkill(descriptionWeaponSkill = uiState?.weaponInfo?.weapon?.description)

            BaseDefaultText(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                text = uiState?.weaponInfo?.weapon?.story ?: "",
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
            )
        }
    }
}