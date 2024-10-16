package com.example.tanorami.info_about_hero.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tanorami.R
import com.example.tanorami.base_build_hero.ui.components.HeaderCategory
import com.example.tanorami.base_components.text.BaseDefaultText
import com.example.tanorami.core.theme.AppTheme
import com.example.tanorami.core.theme.DarkGray
import com.example.tanorami.info_about_hero.data.model.Eidolon

@Composable
fun EidolonsListColumn(
    modifier: Modifier = Modifier,
    eidolonsList: List<Eidolon>,
) {
    HeaderCategory(
        modifier = modifier.padding(top = 24.dp),
        headerCategory = R.string.eidolons
    )

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        eidolonsList.forEach { eidolon ->
            EidolonItem(
                eidolonImage = eidolon.image,
                eidolonTitle = eidolon.title,
                eidolonDescription = eidolon.description
            )
        }
    }
}

@Composable
private fun EidolonItem(
    modifier: Modifier = Modifier,
    eidolonTitle: String,
    eidolonDescription: String,
    eidolonImage: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, DarkGray, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.CenterVertically),
            model = eidolonImage,
            contentDescription = null,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BaseDefaultText(
                text = eidolonTitle,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            )

            BaseDefaultText(
                text = eidolonDescription,
                fontSize = 16.sp,
                lineHeight = 18.sp
            )
        }
    }
}

@Preview
@Composable
private fun EidolonItemPreview() {
    AppTheme {
        EidolonItem(
            eidolonTitle = "Изменение эклиптического метеорита",
            eidolonDescription = "Уровень навыка повышается на 2, максимальный уровень - 15. Уровень таланта повышается на 2, максимальный уровень - 15",
            eidolonImage = ""
        )
    }
}