package com.example.tanorami.create_build_hero.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tanorami.create_build_hero.presentation.models.CreateBuildHeroScreenEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBuildBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onEvent: (CreateBuildHeroScreenEvents) -> Unit,
    content: LazyGridScope.() -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = {
            onEvent(CreateBuildHeroScreenEvents.ChangeStateEquipmentBottomSheet(sheetVisibilityState = false))
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
            content()
        }
    }
}