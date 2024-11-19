package com.example.tanorami.weapons_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarDefaults.inputFieldColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.ui.theme.Black
import com.example.core.ui.theme.Blue
import com.example.core.ui.theme.Orange
import com.example.core.ui.theme.Violet
import com.example.core.ui.theme.White
import com.example.domain.repository.weapon.WeaponModel
import com.example.strings.R
import com.example.tanorami.info_about_weapon.ui.InfoAboutWeaponNavArguments
import com.example.tanorami.weapons_list.presentation.WeaponsListViewModel
import com.example.tanorami.weapons_list.presentation.models.WeaponsListScreenEvents
import com.example.tanorami.weapons_list.presentation.models.WeaponsListScreenSideEffects
import com.example.tanorami.weapons_list.presentation.models.WeaponsListScreenUiState
import com.example.ui.components.text.BaseDefaultText

@Composable
fun WeaponsListScreen(
    viewModelFactory: ViewModelProvider.Factory,
    viewModel: WeaponsListViewModel = viewModel(factory = viewModelFactory),
    navController: NavController,
) {
    val state = viewModel.uiState().collectAsState().value
    val sideEffects = viewModel.uiEffect().collectAsState(initial = null).value

    WeaponsListScreenContent(
        uiState = state,
        onEvent = viewModel::onEvent
    )

    when (sideEffects) {
        is WeaponsListScreenSideEffects.OnInfoAboutWeaponScreen -> {
            navController.navigate(route = InfoAboutWeaponNavArguments(idWeapon = sideEffects.idWeapon))
            viewModel.clearEffect()
        }

        null -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeaponsListScreenContent(
    uiState: WeaponsListScreenUiState,
    onEvent: (WeaponsListScreenEvents) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            inputField = {
                SearchBarDefaults.InputField(
                    query = uiState.searchTextField.value,
                    onQueryChange = { onEvent(WeaponsListScreenEvents.SearchTextChanged(it)) },
                    onSearch = { },
                    expanded = false,
                    onExpandedChange = { },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.enter_weapon_hero)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    colors = inputFieldColors(
                        cursorColor = MaterialTheme.colorScheme.secondary
                    )
                )
            },
            expanded = false,
            onExpandedChange = { },
            content = { }
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 80.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 72.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(if (uiState.searchTextField.value.isEmpty()) uiState.weaponsList else uiState.filteredWeaponsList) { weapon ->
                WeaponItem(
                    weapon = weapon,
                    onClick = { onEvent(WeaponsListScreenEvents.OnWeaponClick(it)) })
            }
        }
    }
}

@Composable
private fun WeaponItem(
    modifier: Modifier = Modifier,
    weapon: WeaponModel,
    onClick: (idWeapon: Int) -> Unit
) {
    Box(
        modifier = modifier
            .height(120.dp)
            .width(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(
                4.dp,
                when (weapon.rarity) {
                    0 -> Blue
                    1 -> Violet
                    2 -> Orange
                    else -> Orange
                },
                RoundedCornerShape(16.dp)
            )
            .clickable { onClick(weapon.idWeapon) },
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = weapon.image,
            contentDescription = null,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.5F to Color.Transparent,
                            1F to Black
                        )
                    )
                )
                .align(Alignment.BottomCenter)
        )

        BaseDefaultText(
            modifier = Modifier
                .padding(bottom = 12.dp, start = 4.dp, end = 4.dp)
                .align(Alignment.BottomCenter),
            text = weapon.name,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            maxLines = 2,
            color = White,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
        )
    }
}