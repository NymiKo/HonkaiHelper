package com.example.tanorami.info_about_decoration.data

interface InfoAboutDecorationRepository {
    suspend fun getDecoration(idDecoration: Int): com.example.common.DecorationModel
}