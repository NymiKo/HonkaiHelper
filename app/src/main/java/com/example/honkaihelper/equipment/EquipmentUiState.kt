package com.example.honkaihelper.equipment

import com.example.honkaihelper.equipment.data.model.Equipment

sealed class EquipmentUiState<out T> {
    object LOADING : EquipmentUiState<Nothing>()
    data class SUCCESS(val data: List<Equipment>) : EquipmentUiState<Nothing>()
    data class ERROR(val message: Int) : EquipmentUiState<Nothing>()
}