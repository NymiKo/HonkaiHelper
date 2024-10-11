package com.example.tanorami.info_about_weapon.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.tanorami.base_components.BaseCenterAlignedTopAppBar
import com.example.tanorami.base_components.BaseDefaultText
import com.example.tanorami.info_about_weapon.data.model.FullInfoAboutWeapon
import com.example.tanorami.info_about_weapon.presentation.InfoAboutWeaponViewModel
import com.example.tanorami.info_about_weapon.ui.components.DescriptionWeaponSkill
import com.example.tanorami.info_about_weapon.ui.components.ImageRarityWeapon
import com.example.tanorami.info_about_weapon.ui.components.ImagesWeaponAndPath
import com.example.tanorami.utils.OnLifecycleEvent

@Composable
fun InfoAboutWeaponScreen(
    idWeapon: Int,
    viewModel: InfoAboutWeaponViewModel,
    navController: NavController,
) {
    val weapon = viewModel.weapon.collectAsStateWithLifecycle().value
    
    InfoAboutWeaponScreenContent(
        weapon = weapon,
        onBack = navController::popBackStack
    )
    
    OnLifecycleEvent { owner, event ->  
        when(event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.getWeapon(idWeapon)
            }
            
            else -> {}
        }
    }
}

@Composable
private fun InfoAboutWeaponScreenContent(
    weapon: FullInfoAboutWeapon?,
    onBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BaseCenterAlignedTopAppBar(
                title = weapon?.weapon?.name ?: "",
                onBack = onBack::invoke
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
                imageWeapon = weapon?.path?.image,
                imagePath = weapon?.weapon?.image,
            )

            ImageRarityWeapon(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                rarity = weapon?.weapon?.rarity
            )

            DescriptionWeaponSkill(descriptionWeaponSkill = weapon?.weapon?.description)

            BaseDefaultText(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                text = weapon?.weapon?.story ?: "",
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif,
            )
        }
    }
}