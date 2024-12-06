package com.example.data.source.decoration

import com.example.data.db.entity.DecorationEntity

interface DecorationLocalDataSource {
    suspend fun getDecorations(): List<DecorationEntity>
    suspend fun getDecoration(idDecoration: Int): DecorationEntity
    suspend fun insertDecorations(decorations: List<DecorationEntity>)
}