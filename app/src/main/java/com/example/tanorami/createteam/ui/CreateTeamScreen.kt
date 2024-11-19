package com.example.tanorami.createteam.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.core.ui.theme.Red
import com.example.domain.repository.hero.model.HeroBaseInfoModel
import com.example.strings.R
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.createteam.presentation.CreateTeamViewModel
import com.example.tanorami.createteam.presentation.models.CreateTeamScreenEvents
import com.example.tanorami.createteam.presentation.models.CreateTeamScreenSideEffects
import com.example.tanorami.createteam.presentation.models.CreateTeamScreenUiState
import com.example.tanorami.createteam.ui.components.ItemHeroAvatar
import com.example.tanorami.createteam.ui.components.ItemHeroAvatarWithName
import com.example.tanorami.utils.OnLifecycleEvent
import com.example.tanorami.utils.toast
import kotlinx.serialization.Serializable

@Serializable
data class CreateTeamNavArguments(val idTeam: Long? = null)

@Composable
fun CreateTeamScreen(
    navArguments: CreateTeamNavArguments,
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: CreateTeamViewModel = viewModel(factory = viewModelFactory),
    navController: NavController
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(null).value
    val context = LocalContext.current

    CreateTeamScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when(sideEffects) {
        CreateTeamScreenSideEffects.OnBack -> {
            navController.popBackStack()
            viewModel.clearEffect()
        }
        CreateTeamScreenSideEffects.TeamDeleted -> {
            toast(context, R.string.team_deleted)
            navController.popBackStack()
            viewModel.clearEffect()
        }
        CreateTeamScreenSideEffects.TeamSaved -> {
            toast(context, R.string.team_saved)
            navController.popBackStack()
            viewModel.clearEffect()
        }
        is CreateTeamScreenSideEffects.ShowToastError -> {
            toast(context, sideEffects.message)
            viewModel.clearEffect()
        }
        null -> {}
    }

    OnLifecycleEvent { owner, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                viewModel.onEvent(CreateTeamScreenEvents.GetTeam(idTeam = navArguments.idTeam))
            }

            else -> {}
        }
    }
}

@Composable
private fun CreateTeamScreenContent(
    uiState: CreateTeamScreenUiState,
    onEvent: (CreateTeamScreenEvents) -> Unit
) {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                uiState = uiState,
                onEvent = onEvent::invoke
            )
        },
        floatingActionButton = {
            SaveAndUpdateTeamButton(
                isCreateTeam = uiState.isCreateTeamMode,
                saveTeam = { onEvent(CreateTeamScreenEvents.SaveTeam) },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            HeroesListInTeam(heroesListInTeam = uiState.heroesListInTeam)
            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            HeroesList(
                heroesList = uiState.heroesList, onEvent = onEvent::invoke
            )
        }
    }
}

@Composable
private fun TopAppBar(
    modifier: Modifier = Modifier,
    uiState: CreateTeamScreenUiState,
    onEvent: (CreateTeamScreenEvents) -> Unit,
) {
    val context = LocalContext.current

    com.example.ui.components.top_app_bar.BaseTopAppBar(
        modifier = modifier,
        title = stringResource(id = if (uiState.isCreateTeamMode) R.string.creating_a_team else R.string.edit_team),
        actions = {
            if (!uiState.isCreateTeamMode) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            val clipboard =
                                context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                            val clipData: ClipData = ClipData.newPlainText("UID", uiState.uidTeam)
                            clipboard.setPrimaryClip(clipData)
                            toast(context, R.string.message_uid_team_copied)
                        },
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary
                    )

                    Icon(
                        modifier = Modifier.clickable {
                            onEvent(
                                CreateTeamScreenEvents.ChangeVisibilityDialogDeleteTeam(
                                    true
                                )
                            )
                        },
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Red
                    )
                }
            }
        },
        onBack = { onEvent(CreateTeamScreenEvents.OnBack) }
    )

    if (uiState.dialogDeleteTeamVisibilityState) {
        com.example.ui.components.dialog.BaseSaveAlertDialog(
            message = R.string.delete_the_command,
            onConfirmation = { onEvent(CreateTeamScreenEvents.DeleteTeam) },
            onDismissRequest = {
                onEvent(
                    CreateTeamScreenEvents.ChangeVisibilityDialogDeleteTeam(
                        false
                    )
                )
            }
        )
    }
}

@Composable
private fun HeroesListInTeam(
    modifier: Modifier = Modifier,
    heroesListInTeam: List<HeroBaseInfoModel>,
) {
    LazyRow(
        modifier = modifier
            .padding(16.dp)
            .height(90.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        items(
            count = heroesListInTeam.size,
            key = { heroesListInTeam[it].id },
        ) {
            ItemHeroAvatar(
                modifier = Modifier.animateItem(), heroBaseInfoProjection = heroesListInTeam[it]
            )
        }
    }
}

@Composable
private fun HeroesList(
    modifier: Modifier = Modifier,
    heroesList: List<ActiveHeroInTeam>,
    onEvent: (CreateTeamScreenEvents) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(count = heroesList.size, key = { heroesList[it].hero.id }) {
            ItemHeroAvatarWithName(
                activeHeroInTeam = heroesList[it], onEvent = onEvent::invoke
            )
        }
    }
}

@Composable
private fun SaveAndUpdateTeamButton(
    modifier: Modifier = Modifier,
    isCreateTeam: Boolean,
    saveTeam: () -> Unit,
) {
    var openSaveTeamDialog by remember { mutableStateOf(false) }

    com.example.ui.components.button.BaseSmallFloatingButton(
        modifier = modifier,
        icon = Icons.Default.Save,
        onClick = { openSaveTeamDialog = true })

    if (openSaveTeamDialog) {
        com.example.ui.components.dialog.BaseSaveAlertDialog(
            message = if (isCreateTeam) R.string.add_the_created_command else R.string.update_the_command,
            onConfirmation = {
                saveTeam()
                openSaveTeamDialog = false
            },
            onDismissRequest = { openSaveTeamDialog = false })
    }
}
