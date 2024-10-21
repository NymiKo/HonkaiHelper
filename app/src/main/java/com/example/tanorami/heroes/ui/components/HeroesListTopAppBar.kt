package com.example.tanorami.heroes.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_components.icon.BaseIcon
import com.example.tanorami.base_components.top_app_bar.BaseTopAppBar
import com.example.tanorami.heroes.presentation.models.HeroesListScreenEvents
import com.example.tanorami.heroes.presentation.models.HeroesListScreenUiState

@Composable
fun HeroesListTopAppBar(
    modifier: Modifier = Modifier,
    uiState: HeroesListScreenUiState,
    onEvent: (HeroesListScreenEvents) -> Unit,
) {
    BaseTopAppBar(
        modifier = modifier,
        title = stringResource(id = R.string.list_of_heroes),
        navigationIcon = false,
        actions = {
            SearchBar(
                modifier = Modifier.weight(1F),
                focus = uiState.searchBarFocus,
                searchValue = uiState.searchTextField.value,
                onSearchValueChange = { onEvent(HeroesListScreenEvents.SearchTextChanged(it)) },
                changeFocus = { onEvent(HeroesListScreenEvents.ChangeSearchBarFocus(it)) }
            )

            AnimatedVisibility(
                visible = !uiState.searchBarFocus
            ) {
                IconButton(
                    onClick = { onEvent(HeroesListScreenEvents.OnSettingsScreen) }
                ) {
                    BaseIcon(icon = Icons.Default.Settings)
                }
            }

            AnimatedVisibility(
                visible = !uiState.searchBarFocus,
            ) {
                IconButton(onClick = { onEvent(HeroesListScreenEvents.OnProfileScreen) }) {
                    AsyncImage(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary, CircleShape),
                        model = uiState.userAvatar.ifEmpty { R.drawable.ic_person },
                        contentDescription = null,
                        colorFilter = if (uiState.userAvatar.isEmpty()) ColorFilter.tint(
                            MaterialTheme.colorScheme.onSecondary
                        ) else null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    )
}