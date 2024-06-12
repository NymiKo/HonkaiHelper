package com.example.tanorami.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.core.theme.Blue
import com.example.tanorami.core.theme.White
import com.example.tanorami.profile.presentation.components.TopAppBarCustom

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {
    ProfileScreenContent(

    )
}

@Composable
private fun ProfileScreenContent(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBarCustom(text = R.string.profile)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            AvatarAndNickname(
                avatarUrl = "",
                nickname = ""
            )
        }
    }
}

@Composable
private fun AvatarAndNickname(
    modifier: Modifier = Modifier,
    avatarUrl: String,
    nickname: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        AvatarImage(avatarUrl = avatarUrl)
    }
}

@Composable
fun AvatarImage(
    modifier: Modifier = Modifier,
    avatarUrl: String
) {
    Box(
        modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        AsyncImage(
            modifier = modifier.fillMaxSize(),
            model = avatarUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        AsyncImage(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .align(Alignment.BottomEnd)
                .background(White),
            model = R.drawable.ic_add,
            colorFilter = ColorFilter.tint(color = Blue),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreenContent()
}

