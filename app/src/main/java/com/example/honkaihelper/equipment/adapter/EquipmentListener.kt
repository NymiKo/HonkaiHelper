package com.example.honkaihelper.equipment.adapter

import com.example.honkaihelper.equipment.data.model.Equipment

interface EquipmentListener {
    fun onClick(equipment: Equipment)
}