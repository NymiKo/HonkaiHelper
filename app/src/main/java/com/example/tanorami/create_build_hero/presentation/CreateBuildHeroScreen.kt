package com.example.tanorami.create_build_hero.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseDefaultText
import com.example.tanorami.base_components.BaseTopAppBar
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.core.theme.DarkGray
import com.example.tanorami.core.theme.Red
import com.example.tanorami.core.theme.White
import com.example.tanorami.create_build_hero.presentation.components.BuildStatsComponent
import com.example.tanorami.create_build_hero.presentation.components.EquipmentBuildComponent
import com.example.tanorami.utils.OnLifecycleEvent

@Composable
fun CreateBuildHeroScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateBuildHeroViewModel,
    idBuild: Long,
    onBack: () -> Unit,
) {
    CreateBuildHeroScreenContent(
        modifier = modifier,
        uiState = viewModel.uiState,
        onEvent = { event ->
            when (event) {
                CreateBuildHeroScreenEvents.OnBack -> onBack()
                else -> Unit
            }
            viewModel.onEvent(event)
        },
        idBuild = idBuild
    )
}

@Composable
private fun CreateBuildHeroScreenContent(
    modifier: Modifier = Modifier,
    uiState: CreateBuildHeroScreenUiState,
    idBuild: Long,
    onEvent: (CreateBuildHeroScreenEvents) -> Unit,
) {
    OnLifecycleEvent { owner, event ->
        when(event) {
            Lifecycle.Event.ON_START -> { onEvent(CreateBuildHeroScreenEvents.GetBuild(idBuild)) }
            else -> { }
        }
    }

    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                isCreateBuild = uiState.isCreateBuild,
                deleteBuild = { onEvent(CreateBuildHeroScreenEvents.DeleteBuild) },
                onBack = { onEvent(CreateBuildHeroScreenEvents.OnBack) }
            )
        },
        floatingActionButton = {
            SaveOrUpdateBuildHeroButton(
                isCreateBuild = uiState.isCreateBuild,
                saveBuild = { onEvent(CreateBuildHeroScreenEvents.SaveBuild) },
                updateBuild = { onEvent(CreateBuildHeroScreenEvents.UpdateBuild) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AvatarHeroImageAndName(
                heroImage = uiState.hero?.avatar ?: "",
                heroName = uiState.hero?.name ?: ""
            )
            EquipmentBuildComponent(
                weaponImage = uiState.buildHeroFromUser?.weapon,
                relicTwoPartsImage = uiState.buildHeroFromUser?.relicTwoParts,
                relicFourPartsImage = uiState.buildHeroFromUser?.relicFourParts,
                decorationImage = uiState.buildHeroFromUser?.decoration,
            )
            BuildStatsComponent()
        }
    }
}

@Composable
fun AvatarHeroImageAndName(
    modifier: Modifier = Modifier,
    heroImage: String,
    heroName: String,
) {
    Box(
        modifier = modifier.width(120.dp)
    ) {
        AsyncImage(
            modifier = Modifier.height(170.dp),
            model = heroImage,
            contentDescription = null,
        )
        BaseDefaultText(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkGray)
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            text = heroName,
            color = White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    isCreateBuild: Boolean,
    deleteBuild: () -> Unit,
    onBack: () -> Unit
) {
    BaseTopAppBar(
        modifier = modifier,
        title = stringResource(id = if (isCreateBuild) R.string.adding_your_build else R.string.edit_build),
        actions = {
            if (!isCreateBuild) {
                Icon(
                    modifier = Modifier.clickable { deleteBuild() },
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = Red
                )
            }
        },
        onBack = { onBack() }
    )
}

@Composable
private fun SaveOrUpdateBuildHeroButton(
    modifier: Modifier = Modifier,
    isCreateBuild: Boolean,
    saveBuild: () -> Unit,
    updateBuild: () -> Unit,
) {
    SmallFloatingActionButton(
        modifier = modifier,
        onClick = {
            if (isCreateBuild) {
                saveBuild()
            } else {
                updateBuild()
            }
        },
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Icon(imageVector = Icons.Default.Save, contentDescription = null)
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CreateBuildHeroScreenPreview(modifier: Modifier = Modifier) {
    AppTheme {
        CreateBuildHeroScreenContent(
            modifier = modifier,
            uiState = CreateBuildHeroScreenUiState(),
            onEvent = {},
            idBuild = 1L,
        )
    }
}