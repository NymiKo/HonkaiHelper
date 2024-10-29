package com.example.tanorami.main.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.tanorami.R
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.core.theme.DarkGrey
import com.example.tanorami.main.presentation.models.MainScreenEvents

@Composable
fun CreateBuildOrTeamDialog(
    modifier: Modifier = Modifier,
    onEvent: (MainScreenEvents) -> Unit,
) {
    Dialog(onDismissRequest = {
        onEvent(
            MainScreenEvents.ChangeVisibilityDialogCreateBuildOrTeam(
                false
            )
        )
    }) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
        ) {
            DialogItem(
                text = stringResource(id = R.string.add_a_build),
                onClick = {
                    onEvent(MainScreenEvents.OnDialogItemCreateBuildClick)
                }
            )
            HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = DarkGrey)
            DialogItem(
                text = stringResource(id = R.string.add_a_team),
                onClick = { onEvent(MainScreenEvents.OnDialogItemCreateTeamClick) }
            )
        }
    }
}

@Composable
private fun DialogItem(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    BaseDefaultText(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        text = text,
        textAlign = TextAlign.Center
    )
}