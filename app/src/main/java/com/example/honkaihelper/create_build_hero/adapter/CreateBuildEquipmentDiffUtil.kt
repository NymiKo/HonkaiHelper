package com.example.honkaihelper.create_build_hero.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.honkaihelper.equipment.data.model.Equipment

class CreateBuildEquipmentDiffUtil : DiffUtil.ItemCallback<Equipment>() {
    override fun areItemsTheSame(oldItem: Equipment, newItem: Equipment): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Equipment, newItem: Equipment): Boolean {
        return oldItem == newItem
    }
}