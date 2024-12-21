package com.example.teams_and_builds.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.common.HeroBuildModel
import com.example.core.ui.theme.DarkGrey
import com.example.core.ui.theme.LightGray
import com.example.strings.R
import com.example.ui.components.button.BaseButton
import com.example.ui.components.hero_build.BuildItem
import com.example.ui.components.text_field.BaseOutlinedTextField
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun BuildsListContent(
    modifier: Modifier = Modifier,
    buildsList: List<HeroBuildModel>,
    refreshingBuildsList: Boolean,
    uidBuildFieldValue: String,
    refreshBuildsList: () -> Unit,
    uidBuildFieldChanged: (newValue: String) -> Unit,
    onBuildClick: (idBuild: Long) -> Unit,
    onDoneButtonClick: () -> Unit,
) {
    val refreshState = rememberPullRefreshState(
        refreshing = refreshingBuildsList,
        onRefresh = refreshBuildsList::invoke
    )
    val listState = rememberLazyListState()
    var isButtonVisible = remember { mutableStateOf(true) }
    var previousScrollOffset = remember { mutableIntStateOf(0) }
    val filtersBuildsSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var sheetVisible = remember { mutableStateOf(false) }
    var targetVisibility = remember { mutableStateOf(true) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentScrollOffset ->
                targetVisibility.value = currentScrollOffset <= previousScrollOffset.intValue
                previousScrollOffset.intValue = currentScrollOffset
            }
    }

    LaunchedEffect(targetVisibility.value) {
        delay(150) // Задержка для debounce
        isButtonVisible.value = targetVisibility.value
    }

    Box(modifier = modifier.pullRefresh(refreshState)) {
        Column(verticalArrangement = Arrangement.Center) {
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.End),
                visible = isButtonVisible.value,
            ) {
                FilterButton(
                    modifier = Modifier.padding(bottom = 16.dp, end = 16.dp),
                    onClick = { sheetVisible.value = true })
            }
            LazyColumn(
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = listState,
            ) {
                items(buildsList, key = { it.idBuild }) { buildHero ->
                    BuildItem(
                        heroBuild = buildHero,
                        onClick = { onBuildClick(buildHero.idBuild) }
                    )
                }
            }
        }

        PullRefreshIndicator(
            refreshing = refreshingBuildsList,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        if (sheetVisible.value) {
            FiltersBuildsColumnBottomSheet(
                sheetState = filtersBuildsSheetState,
                sheetVisible = sheetVisible,
                uidBuildFieldValue = uidBuildFieldValue,
                uidBuildFieldChanged = uidBuildFieldChanged::invoke,
                onDoneButtonClick = onDoneButtonClick::invoke
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FiltersBuildsColumnBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    sheetVisible: MutableState<Boolean>,
    uidBuildFieldValue: String,
    uidBuildFieldChanged: (newValue: String) -> Unit,
    onDoneButtonClick: () -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = { sheetVisible.value = false },
    ) {
        BaseOutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = uidBuildFieldValue,
            onValueChanged = uidBuildFieldChanged::invoke,
            singleLine = true,
            label = stringResource(R.string.enter_build_uid),
            enabled = true,
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(LightGray, CircleShape)
                        .clickable { uidBuildFieldChanged("") },
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null,
                    tint = DarkGrey,
                )
            }
        )

        BaseButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.done)
        ) {
            onDoneButtonClick()
            sheetVisible.value = false
        }
    }
}