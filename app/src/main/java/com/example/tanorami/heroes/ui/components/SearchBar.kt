package com.example.tanorami.heroes.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tanorami.R
import com.example.tanorami.base_components.icon.BaseIcon
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.core.theme.DarkGrey
import com.example.tanorami.core.theme.Grey

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchValue: String,
    focus: Boolean,
    onSearchValueChange: (newValue: String) -> Unit,
    changeFocus: (focus: Boolean) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier.background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AnimatedVisibility(visible = focus) {
            IconButton(onClick = {
                changeFocus(focus)
                focusManager.clearFocus()
                keyboardController?.hide()
            }) {
                BaseIcon(icon = Icons.AutoMirrored.Filled.ArrowBack)
            }
        }

        SearchTextField(
            modifier = Modifier
                .padding(
                    top = 12.dp,
                    bottom = 12.dp,
                    end = 12.dp,
                    start = 12.dp
                )
                .weight(1F),
            value = searchValue,
            onValueChange = onSearchValueChange::invoke,
            changeFocus = changeFocus::invoke
        )
    }
}

@Composable
private fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (newValue: String) -> Unit,
    changeFocus: (focus: Boolean) -> Unit,
) {
    Box(
        modifier = modifier
            .height(56.dp)
            .clip(CircleShape)
            .background(DarkGrey, CircleShape),
        contentAlignment = Alignment.CenterStart,
    ) {
        if (value.isEmpty()) {
            BaseDefaultText(
                modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                text = stringResource(id = R.string.enter_name_hero),
                color = Grey,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                modifier = Modifier
                    .weight(1F)
                    .onFocusChanged {
                        changeFocus(it.isFocused)
                    },
                value = value,
                onValueChange = onValueChange::invoke,
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.secondary,
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary)
            )

            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}