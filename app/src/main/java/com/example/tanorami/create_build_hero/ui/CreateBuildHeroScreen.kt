package com.example.tanorami.create_build_hero.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_components.button.BaseSmallFloatingButton
import com.example.tanorami.base_components.dialog.BaseSaveAlertDialog
import com.example.tanorami.base_components.top_app_bar.BaseTopAppBar
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.core.theme.Blue
import com.example.tanorami.core.theme.Orange
import com.example.tanorami.core.theme.Red
import com.example.tanorami.core.theme.Violet
import com.example.tanorami.create_build_hero.presentation.CreateBuildHeroViewModel
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenEvents
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenSideEffects
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenUiState
import com.example.tanorami.create_build_hero.presentation.models.EquipmentType
import com.example.tanorami.create_build_hero.ui.components.AvatarHeroImageAndName
import com.example.tanorami.create_build_hero.ui.components.BuildStatsComponent
import com.example.tanorami.create_build_hero.ui.components.EquipmentBuildComponent
import com.example.tanorami.utils.OnLifecycleEvent
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class CreateBuildForHeroNavArguments(val idBuild: Long? = null, val idHero: Int? = null)

@Composable
fun CreateBuildHeroScreen(
    navArguments: CreateBuildForHeroNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: CreateBuildHeroViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffect = viewModel.uiEffect().collectAsState(initial = null).value

    CreateBuildHeroScreenContent(uiState = state, onEvent = viewModel::onEvent,)

    when (sideEffect) {
        CreateBuildHeroScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }
        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(
                    CreateBuildHeroScreenEvents.GetBuild(
                        idBuild = navArguments.idBuild,
                        idHero = navArguments.idHero
                    )
                )
            }

            else -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateBuildHeroScreenContent(
    uiState: CreateBuildHeroScreenUiState,
    onEvent: (CreateBuildHeroScreenEvents) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.background(MaterialTheme.colorScheme.background), topBar = {
        TopAppBar(
            isCreateBuild = uiState.isCreateBuildMode,
            uidBuild = uiState.buildHeroFromUser.uid,
            deleteBuild = { onEvent(CreateBuildHeroScreenEvents.DeleteBuild) },
            onBack = { onEvent(CreateBuildHeroScreenEvents.OnBack) }
        )
    },
        floatingActionButton = {
            SaveOrUpdateBuildHeroButton(isCreateBuild = uiState.isCreateBuildMode,
                saveBuild = { onEvent(CreateBuildHeroScreenEvents.SaveBuild) },
                updateBuild = { onEvent(CreateBuildHeroScreenEvents.UpdateBuild) },
                isSuccess = uiState.isSuccess,
                onBack = { onEvent(CreateBuildHeroScreenEvents.OnBack) }
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AvatarHeroImageAndName(
                heroImage = uiState.hero?.avatar, heroName = uiState.hero?.name
            )

            EquipmentBuildComponent(
                weapon = uiState.buildHeroFromUser.weapon,
                relicTwoParts = uiState.buildHeroFromUser.relicTwoParts,
                relicFourParts = uiState.buildHeroFromUser.relicFourParts,
                decoration = uiState.buildHeroFromUser.decoration,
                onWeaponClick = {
                    onEvent(
                        CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(
                            sheetState = true,
                            equipmentType = EquipmentType.WEAPON
                        )
                    )
                },
                onTwoPartsRelicClick = {
                    onEvent(
                        CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(
                            sheetState = true,
                            equipmentType = EquipmentType.RELIC_TWO_PARTS,
                        )
                    )
                },
                onFourPartsRelicClick = {
                    onEvent(
                        CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(
                            sheetState = true,
                            equipmentType = EquipmentType.RELIC_FOUR_PARTS,
                        )
                    )
                },
                onDecorationClick = {
                    onEvent(
                        CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(
                            sheetState = true,
                            equipmentType = EquipmentType.DECORATION,
                        )
                    )
                }
            )

            BuildStatsComponent(
                currentValue = uiState.buildHeroFromUser.statsEquipmentList,
                changeStatOnBody = { value ->
                    onEvent(CreateBuildHeroScreenEvents.ChangeStatsOnBody(value = value))
                },
                changeStatOnLegs = { value ->
                    onEvent(CreateBuildHeroScreenEvents.ChangeStatsOnLegs(value = value))
                },
                changeStatOnSphere = { value ->
                    onEvent(CreateBuildHeroScreenEvents.ChangeStatsOnSphere(value = value))
                },
                changeStatOnRope = { value ->
                    onEvent(CreateBuildHeroScreenEvents.ChangeStatsOnRope(value = value))
                },
            )

            if (uiState.showBottomSheet) {
                ModalBottomSheet(
                    modifier = Modifier.padding(innerPadding),
                    onDismissRequest = {
                        onEvent(CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(sheetState = false))
                    },
                    sheetState = sheetState
                ) {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        columns = GridCells.Adaptive(minSize = 80.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        items(uiState.equipmentList) { equipment ->
                            AsyncImage(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        when (equipment.rarity) {
                                            0 -> Blue
                                            1 -> Violet
                                            2 -> Orange
                                            else -> Orange
                                        },
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        when (uiState.equipmentType) {
                                            EquipmentType.WEAPON -> onEvent(
                                                CreateBuildHeroScreenEvents.AddWeapon(equipment)
                                            )

                                            EquipmentType.RELIC_TWO_PARTS -> onEvent(
                                                CreateBuildHeroScreenEvents.AddTwoPartsRelic(
                                                    equipment
                                                )
                                            )

                                            EquipmentType.RELIC_FOUR_PARTS -> onEvent(
                                                CreateBuildHeroScreenEvents.AddFourPartsRelic(
                                                    equipment
                                                )
                                            )

                                            EquipmentType.DECORATION -> onEvent(
                                                CreateBuildHeroScreenEvents.AddDecoration(equipment)
                                            )
                                        }
                                        coroutineScope
                                            .launch { sheetState.hide() }
                                            .invokeOnCompletion {
                                                if (!sheetState.isVisible) {
                                                    onEvent(
                                                        CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(
                                                            sheetState = false
                                                        )
                                                    )
                                                }
                                            }
                                    },
                                model = equipment.image,
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    isCreateBuild: Boolean,
    uidBuild: String,
    deleteBuild: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var openDeleteBuildAlertDialog by remember { mutableStateOf(false) }

    BaseTopAppBar(modifier = modifier,
        title = stringResource(id = if (isCreateBuild) R.string.adding_your_build else R.string.edit_build),
        actions = {
            if (!isCreateBuild) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            val clipboard =
                                context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                            val clipData: ClipData = ClipData.newPlainText("UID", uidBuild)
                            clipboard.setPrimaryClip(clipData)
                            Toast.makeText(
                                context,
                                R.string.message_uid_build_copied,
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )

                    Icon(
                        modifier = Modifier.clickable { openDeleteBuildAlertDialog = true },
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Red
                    )
                }
            }
        },
        onBack = { onBack() })

    if (openDeleteBuildAlertDialog) {
        BaseSaveAlertDialog(
            message = R.string.delete_the_build,
            onConfirmation = {
                openDeleteBuildAlertDialog = false
                deleteBuild()
                onBack()
            },
            onDismissRequest = { openDeleteBuildAlertDialog = false }
        )
    }
}

@Composable
private fun SaveOrUpdateBuildHeroButton(
    modifier: Modifier = Modifier,
    isCreateBuild: Boolean,
    isSuccess: Boolean,
    saveBuild: () -> Unit,
    updateBuild: () -> Unit,
    onBack: () -> Unit,
) {
    var openSaveBuildDialog by remember { mutableStateOf(false) }

    BaseSmallFloatingButton(
        modifier = modifier,
        icon = Icons.Default.Save,
        onClick = { openSaveBuildDialog = true }
    )

    if (openSaveBuildDialog) {
        BaseSaveAlertDialog(
            message = if (isCreateBuild) R.string.add_the_created_build else R.string.update_the_build,
            onConfirmation = {
                if (isCreateBuild) {
                    saveBuild()
                } else {
                    updateBuild()
                }
                if (isSuccess) {
                    openSaveBuildDialog = false
                    onBack()
                }
            },
            onDismissRequest = { openSaveBuildDialog = false }
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CreateBuildHeroScreenPreview(modifier: Modifier = Modifier) {
    AppTheme {
        CreateBuildHeroScreenContent(
            uiState = CreateBuildHeroScreenUiState(),
            onEvent = {},
        )
    }
}