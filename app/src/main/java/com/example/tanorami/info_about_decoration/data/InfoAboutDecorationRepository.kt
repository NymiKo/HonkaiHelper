package com.example.tanorami.info_about_decoration.data

import com.example.core.domain.repository.decoration.Decoration

interface InfoAboutDecorationRepository {
    suspend fun getDecoration(idDecoration: Int): Decoration
}