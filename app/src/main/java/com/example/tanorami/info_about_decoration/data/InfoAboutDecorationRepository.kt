package com.example.tanorami.info_about_decoration.data

import com.example.tanorami.info_about_hero.data.model.Decoration

interface InfoAboutDecorationRepository {
    suspend fun getDecoration(idDecoration: Int): Decoration
}