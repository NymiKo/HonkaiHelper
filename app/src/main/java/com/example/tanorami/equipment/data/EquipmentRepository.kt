package com.example.tanorami.equipment.data

import com.example.tanorami.equipment.data.model.Equipment

interface EquipmentRepository {
    suspend fun getWeapons(path: Int): List<Equipment>
    suspend fun getRelics(): List<Equipment>
    suspend fun getDecorations(): List<Equipment>
}