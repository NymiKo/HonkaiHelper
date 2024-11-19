package com.example.tanorami.profile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.strings.R

@Composable
fun UserNotLoggedComponent(modifier: Modifier  = Modifier, onLoginScreen: () -> Unit) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            UserNotLoggedText()
            UserNotLoggedButton(onLoginScreen = onLoginScreen::invoke)
        }
    }
}

@Composable
private fun UserNotLoggedText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        text = stringResource(R.string.not_authorized),
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.secondary,
    )
}

@Composable
private fun UserNotLoggedButton(modifier: Modifier = Modifier, onLoginScreen: () -> Unit) {
    Button(
        modifier = modifier,
        onClick  = {
            onLoginScreen()
        },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
    ) {
        Text(
            text = stringResource(R.string.enter),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}