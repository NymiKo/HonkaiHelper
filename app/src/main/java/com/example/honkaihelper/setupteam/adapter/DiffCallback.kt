package com.example.honkaihelper.setupteam.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.honkaihelper.setupteam.data.model.SetupHero

class DiffCallback : DiffUtil.ItemCallback<SetupHero>() {
    override fun areItemsTheSame(oldItem: SetupHero, newItem: SetupHero): Boolean {
        return oldItem.hero == newItem.hero
    }

    override fun areContentsTheSame(oldItem: SetupHero, newItem: SetupHero): Boolean {
        return oldItem == newItem
    }
}