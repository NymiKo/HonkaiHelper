package com.example.tanorami.info_about_decoration.data

import com.example.domain.repository.decoration.DecorationModel

interface InfoAboutDecorationRepository {
    suspend fun getDecoration(idDecoration: Int): DecorationModel
}