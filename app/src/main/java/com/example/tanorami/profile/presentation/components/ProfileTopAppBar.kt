package com.example.tanorami.profile.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tanorami.R
import com.example.tanorami.base_components.BaseTopAppBar

@Composable
fun ProfileTopAppBar(
    modifier: Modifier = Modifier,
    onEditNicknameScreen: () -> Unit,
    logoutAccountClick: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }

    BaseTopAppBar(
        modifier = modifier,
        title = R.string.profile,
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                MenuItemChangeNickname(onEditNicknameScreen = onEditNicknameScreen::invoke)
                MenuItemLogoutAccount(logoutAccountClick = logoutAccountClick::invoke)
            }
        }
    )
}

@Composable
private fun MenuItemChangeNickname(
    modifier: Modifier = Modifier,
    onEditNicknameScreen: () -> Unit
) {
    DropdownMenuItem(
        modifier = modifier,
        text = {
            Text(
                text = stringResource(id = R.string.edit_nickname),
                color = MaterialTheme.colorScheme.secondary
            )
        },
        onClick = { onEditNicknameScreen() },
        trailingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null, tint = MaterialTheme.colorScheme.secondary) }
    )
}

@Composable
private fun MenuItemLogoutAccount(
    modifier: Modifier = Modifier,
    logoutAccountClick: () -> Unit
) {
    DropdownMenuItem(
        modifier = modifier,
        text = {
            Text(
                text = stringResource(id = R.string.logout_of_your_account),
                color = MaterialTheme.colorScheme.secondary
            )
        },
        onClick = { logoutAccountClick() },
        trailingIcon = { Icon(imageVector = Icons.AutoMirrored.Filled.ExitToApp, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)  }
    )
}

@Preview
@Composable
private fun ProfileTopAppBarPreview() {
    ProfileTopAppBar(
        onEditNicknameScreen = {},
        logoutAccountClick = {}
    )
}