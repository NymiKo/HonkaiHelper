package com.example.tanorami.equipment.adapter

import com.example.tanorami.equipment.data.model.Equipment

interface EquipmentListener {
    fun onClick(equipment: Equipment)
}