package com.example.tanorami.decoration.data

import com.example.tanorami.info_about_hero.data.model.Decoration

interface DecorationInfoRepository {
    suspend fun getDecoration(idDecoration: Int): Decoration
}