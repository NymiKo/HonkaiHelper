package com.example.tanorami.create_build_hero.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.tanorami.equipment.EquipmentType
import com.example.tanorami.utils.OnLifecycleEvent
import com.example.tanorami.viewing_users_build.ViewingUsersBuildUiState

@Composable
fun CreateBuildHeroScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateBuildHeroViewModel,
    idBuild: Long,
    idHero: Int,
    onEquipmentScreen: (pathHero: Int, equipmentType: EquipmentType) -> Unit,
    onBack: () -> Unit,
) {
    CreateBuildHeroScreenContent(
        modifier = modifier,
        uiState = viewModel.uiState,
        onEvent = { event ->
            when (event) {
                is CreateBuildHeroScreenEvents.OnEquipmentScreen -> onEquipmentScreen(
                    event.pathHero, event.equipmentType
                )

                CreateBuildHeroScreenEvents.OnBack -> onBack()
                else -> Unit
            }
            viewModel.onEvent(event)
        },
        idBuild = idBuild,
        idHero = idHero,
    )
}

@Composable
private fun CreateBuildHeroScreenContent(
    modifier: Modifier = Modifier,
    uiState: CreateBuildHeroScreenUiState,
    idBuild: Long,
    idHero: Int,
    onEvent: (CreateBuildHeroScreenEvents) -> Unit,
) {
    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                onEvent(CreateBuildHeroScreenEvents.GetHero(idHero = idHero))
                onEvent(CreateBuildHeroScreenEvents.GetBuild(idBuild))
            }

            else -> {}
        }
    }

    Scaffold(modifier = modifier.background(MaterialTheme.colorScheme.background), topBar = {
        TopAppBar(
            isCreateBuild = uiState.isCreateBuild,
            uidBuild = uiState.buildHeroFromUser.uid,
            deleteBuild = { onEvent(CreateBuildHeroScreenEvents.DeleteBuild) },
            onBack = { onEvent(CreateBuildHeroScreenEvents.OnBack) }
        )
    },
        floatingActionButton = {
        SaveOrUpdateBuildHeroButton(isCreateBuild = uiState.isCreateBuild,
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
                heroImage = uiState.hero?.avatar ?: "", heroName = uiState.hero?.name ?: ""
            )
            EquipmentBuildComponent(weapon = uiState.buildHeroFromUser.weapon,
                relicTwoParts = uiState.buildHeroFromUser.relicTwoParts,
                relicFourParts = uiState.buildHeroFromUser.relicFourParts,
                decoration = uiState.buildHeroFromUser.decoration,
                onEquipmentScreen = { equipmentType ->
                    onEvent(
                        CreateBuildHeroScreenEvents.OnEquipmentScreen(
                            uiState.hero?.path!!, equipmentType
                        )
                    )
                })
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
        }
    }
}

@Composable
fun AvatarHeroImageAndName(
    modifier: Modifier = Modifier,
    heroImage: String,
    heroName: String,
) {
    Column(
        modifier = modifier.width(120.dp)
    ) {
        AsyncImage(
            modifier = Modifier.height(150.dp),
            model = heroImage,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        BaseDefaultText(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkGray)
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
                            val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                            val clipData: ClipData = ClipData.newPlainText("UID", uidBuild)
                            clipboard.setPrimaryClip(clipData)
                            Toast.makeText(context, R.string.message_uid_build_copied, Toast.LENGTH_SHORT).show()
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
        DeleteBuildAlertDialog(
            onDismissRequest = { openDeleteBuildAlertDialog = false },
            onConfirmation = {
                openDeleteBuildAlertDialog = false
                deleteBuild()
                onBack()
            }
        )
    }
}

@Composable
private fun DeleteBuildAlertDialog(
    modifier: Modifier = Modifier,
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(modifier = modifier, text = {
        BaseDefaultText(
            text = stringResource(id = R.string.delete_the_build),
            color = MaterialTheme.colorScheme.secondary,
        )
    }, onDismissRequest = { onDismissRequest() }, confirmButton = {
        TextButton(onClick = { onConfirmation() }) {
            BaseDefaultText(
                text = stringResource(id = R.string.yes),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }, dismissButton = {
        TextButton(onClick = { onDismissRequest() }) {
            BaseDefaultText(
                text = stringResource(id = R.string.cancellation),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    })
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

    SmallFloatingActionButton(
        modifier = modifier,
        onClick = {
            openSaveBuildDialog = true
        },
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Icon(imageVector = Icons.Default.Save, contentDescription = null)
    }

    if (openSaveBuildDialog) {
        SaveBuildAlertDialog(
            isCreateBuild = isCreateBuild,
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

@Composable
private fun SaveBuildAlertDialog(
    modifier: Modifier = Modifier,
    isCreateBuild: Boolean,
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(modifier = modifier, text = {
        BaseDefaultText(
            text = stringResource(
                id = if (isCreateBuild) R.string.add_the_created_build else R.string.update_the_build
            ),
            color = MaterialTheme.colorScheme.secondary,
        )
    }, onDismissRequest = { onDismissRequest() }, confirmButton = {
        TextButton(onClick = { onConfirmation() }) {
            BaseDefaultText(
                text = stringResource(id = R.string.yes),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }, dismissButton = {
        TextButton(onClick = { onDismissRequest() }) {
            BaseDefaultText(
                text = stringResource(id = R.string.cancellation),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    })
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
            idHero = 1,
        )
    }
}