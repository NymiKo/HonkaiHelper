package com.example.tanorami.create_build_hero.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tanorami.R
import com.example.tanorami.core.ui.base_components.button.BaseSmallFloatingButton
import com.example.tanorami.core.ui.base_components.dialog.BaseSaveAlertDialog
import com.example.tanorami.core.ui.base_components.top_app_bar.BaseTopAppBar
import com.example.tanorami.core.ui.theme.AppTheme
import com.example.tanorami.core.ui.theme.Red
import com.example.tanorami.create_build_hero.presentation.CreateBuildHeroViewModel
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenEvents
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenSideEffects
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenUiState
import com.example.tanorami.create_build_hero.presentation.models.EquipmentType
import com.example.tanorami.create_build_hero.ui.components.AvatarHeroImageAndName
import com.example.tanorami.create_build_hero.ui.components.BuildStatsComponent
import com.example.tanorami.create_build_hero.ui.components.CreateBuildBottomSheet
import com.example.tanorami.create_build_hero.ui.components.EquipmentBuildComponent
import com.example.tanorami.create_build_hero.ui.components.EquipmentItem
import com.example.tanorami.main.ui.MainRoute
import com.example.tanorami.utils.OnLifecycleEvent
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class CreateBuildForHeroNavArguments(val idBuild: Long? = null, val idHero: Int? = null)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CreateBuildHeroScreen(
    navArguments: CreateBuildForHeroNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: CreateBuildHeroViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffect = viewModel.uiEffect().collectAsState(initial = null).value

    with(sharedTransitionScope) {
        CreateBuildHeroScreenContent(
            modifier = Modifier.sharedBounds(
                rememberSharedContentState(key = "buildHero-${navArguments.idBuild}"),
                animatedVisibilityScope = animatedVisibilityScope
            ),
            uiState = state,
            onEvent = viewModel::onEvent
        )
    }

    when (sideEffect) {
        CreateBuildHeroScreenSideEffects.OnMainScreen -> {
            navController.popBackStack(route = MainRoute, inclusive = false)
            viewModel.clearEffect()
        }

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
    modifier: Modifier = Modifier,
    uiState: CreateBuildHeroScreenUiState,
    onEvent: (CreateBuildHeroScreenEvents) -> Unit,
) {
    val equipmentSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                isCreateBuild = uiState.isCreateBuildMode,
                uidBuild = uiState.buildHeroFromUser.uid,
                dialogVisibilityState = uiState.dialogDeleteBuildVisibilityState,
                deleteBuild = { onEvent(CreateBuildHeroScreenEvents.DeleteBuild) },
                onBack = { onEvent(CreateBuildHeroScreenEvents.OnBack) },
                changeVisibilityState = {
                    onEvent(
                        CreateBuildHeroScreenEvents.ChangeVisibilityDialogDeleteBuild(
                            it
                        )
                    )
                }
            )
        },
        floatingActionButton = {
            SaveOrUpdateBuildHeroButton(isCreateBuild = uiState.isCreateBuildMode,
                dialogVisibilityState = uiState.dialogSaveBuildVisibilityState,
                saveBuild = { onEvent(CreateBuildHeroScreenEvents.SaveBuild) },
                changeVisibilityState = {
                    onEvent(
                        CreateBuildHeroScreenEvents.ChangeVisibilityDialogSaveBuild(
                            it
                        )
                    )
                }
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
                heroImage = uiState.hero?.avatar,
                heroName = uiState.hero?.name,
                heroRarity = uiState.hero?.rarity
            )

            EquipmentBuildComponent(
                weapon = uiState.buildHeroFromUser.weapon,
                relicTwoParts = uiState.buildHeroFromUser.relicTwoParts,
                relicFourParts = uiState.buildHeroFromUser.relicFourParts,
                decoration = uiState.buildHeroFromUser.decoration,
                onWeaponClick = {
                    onEvent(
                        CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(
                            sheetVisibilityState = true,
                            equipmentType = EquipmentType.WEAPON
                        )
                    )
                },
                onTwoPartsRelicClick = {
                    onEvent(
                        CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(
                            sheetVisibilityState = true,
                            equipmentType = EquipmentType.RELIC_TWO_PARTS,
                        )
                    )
                },
                onFourPartsRelicClick = {
                    onEvent(
                        CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(
                            sheetVisibilityState = true,
                            equipmentType = EquipmentType.RELIC_FOUR_PARTS,
                        )
                    )
                },
                onDecorationClick = {
                    onEvent(
                        CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(
                            sheetVisibilityState = true,
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

            if (uiState.bottomSheetEquipmentVisibilityState) {
                CreateBuildBottomSheet(
                    modifier = Modifier.padding(innerPadding),
                    sheetState = equipmentSheetState,
                    onEvent = onEvent::invoke,
                ) {
                    items(uiState.equipmentList) { equipment ->
                        EquipmentItem(
                            equipment = equipment,
                            equipmentType = uiState.equipmentType,
                            onEvent = onEvent::invoke,
                            hideBottomSheet = {
                                coroutineScope
                                    .launch { equipmentSheetState.hide() }
                                    .invokeOnCompletion {
                                        if (!equipmentSheetState.isVisible) {
                                            onEvent(
                                                CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(
                                                    sheetVisibilityState = false
                                                )
                                            )
                                        }
                                    }
                            }
                        )
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
    dialogVisibilityState: Boolean,
    deleteBuild: () -> Unit,
    changeVisibilityState: (visibility: Boolean) -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current

    BaseTopAppBar(modifier = modifier,
        title = stringResource(id = if (isCreateBuild) R.string.adding_your_build else R.string.edit_build),
        actions = {
            if (!isCreateBuild) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
//                    Icon(
//                        modifier = Modifier.clickable {
//                            val clipboard =
//                                context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
//                            val clipData: ClipData = ClipData.newPlainText("UID", uidBuild)
//                            clipboard.setPrimaryClip(clipData)
//                            Toast.makeText(
//                                context,
//                                R.string.message_uid_build_copied,
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        },
//                        imageVector = Icons.Default.ContentCopy,
//                        contentDescription = null,
//                        tint = MaterialTheme.colorScheme.secondary
//                    )

                    Icon(
                        modifier = Modifier.clickable { changeVisibilityState(true) },
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Red
                    )
                }
            }
        },
        onBack = { onBack() })

    if (dialogVisibilityState) {
        BaseSaveAlertDialog(
            message = R.string.delete_the_build,
            onConfirmation = {
                deleteBuild()
            },
            onDismissRequest = { changeVisibilityState(false) }
        )
    }
}

@Composable
private fun SaveOrUpdateBuildHeroButton(
    modifier: Modifier = Modifier,
    isCreateBuild: Boolean,
    dialogVisibilityState: Boolean,
    saveBuild: () -> Unit,
    changeVisibilityState: (visibility: Boolean) -> Unit,
) {
    BaseSmallFloatingButton(
        modifier = modifier,
        icon = Icons.Default.Save,
        onClick = { changeVisibilityState(true) }
    )

    if (dialogVisibilityState) {
        BaseSaveAlertDialog(
            message = if (isCreateBuild) R.string.add_the_created_build else R.string.update_the_build,
            onConfirmation = {
                saveBuild()
            },
            onDismissRequest = { changeVisibilityState(false) }
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