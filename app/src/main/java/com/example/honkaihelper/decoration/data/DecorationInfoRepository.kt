package com.example.honkaihelper.decoration.data

import com.example.honkaihelper.info_about_hero.data.model.Decoration

interface DecorationInfoRepository {
    suspend fun getDecoration(idDecoration: Int): Decoration
}