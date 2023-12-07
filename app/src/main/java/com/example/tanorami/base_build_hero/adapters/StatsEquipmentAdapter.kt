package com.example.tanorami.base_build_hero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tanorami.R
import com.example.tanorami.base.BaseAdapter
import com.example.tanorami.databinding.ItemStatsEquipmentBaseBuildHeroBinding
import com.example.tanorami.utils.load

class StatsEquipmentAdapter :
    BaseAdapter<ItemStatsEquipmentBaseBuildHeroBinding, String>() {

    private val iconList = listOf(
        R.drawable.relic_piece_body,
        R.drawable.relic_piece_legs,
        R.drawable.relic_piece_sphere,
        R.drawable.relic_piece_rope
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStatsEquipmentBaseBuildHeroBinding.inflate(layoutInflater, parent, false)

        return StatsEquipmentViewHolder(binding)
    }

    private inner class StatsEquipmentViewHolder(private val binding: ItemStatsEquipmentBaseBuildHeroBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: String) {
            binding.imageStatsEquipment.load(iconList[adapterPosition])
            binding.textStatsEquipment.text = model
        }
    }
}