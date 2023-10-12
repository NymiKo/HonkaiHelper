package com.example.honkaihelper.equipment.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.equipment.data.model.Equipment

interface EquipmentRepository {
    suspend fun getWeapon(path: Int): NetworkResult<List<Equipment>>
    suspend fun getRelic(): NetworkResult<List<Equipment>>
    suspend fun getDecoration(): NetworkResult<List<Equipment>>
}