package com.example.tanorami.viewing_users_build.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tanorami.R
import com.example.tanorami.base.BaseAdapter
import com.example.tanorami.databinding.ItemStatsEquipmentBaseBuildHeroBinding
import com.example.tanorami.utils.load

class ViewingUsersBuildStatsAdapter: BaseAdapter<ItemStatsEquipmentBaseBuildHeroBinding, String>() {

    private val iconList = listOf(
        R.drawable.relic_piece_body,
        R.drawable.relic_piece_legs,
        R.drawable.relic_piece_sphere,
        R.drawable.relic_piece_rope
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemStatsEquipmentBaseBuildHeroBinding.inflate(layoutInflater, parent, false)

        return ViewingUsersBuildStatsViewHolder(binding)
    }

    private inner class ViewingUsersBuildStatsViewHolder(private val binding: ItemStatsEquipmentBaseBuildHeroBinding): BaseViewHolder(binding) {
        override fun bind(model: String) {
            binding.textStatsEquipment.text = model
            binding.imageStatsEquipment.load(iconList[adapterPosition])
        }
    }
}