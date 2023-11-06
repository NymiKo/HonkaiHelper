package com.example.honkaihelper.base_build_hero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.databinding.ItemWeaponBaseBuildHeroBinding
import com.example.honkaihelper.info_about_hero.data.model.Relic
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load

class RelicsAdapter: BaseAdapter<ItemWeaponBaseBuildHeroBinding, Relic>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemWeaponBaseBuildHeroBinding.inflate(layoutInflater, parent, false)

        return RelicsViewHolder(binding)
    }

    private inner class RelicsViewHolder(private val binding: ItemWeaponBaseBuildHeroBinding): BaseViewHolder(binding) {
        override fun bind(model: Relic) {
            binding.imageWeaponBaseBuildHero.load(model.image)
            if (adapterPosition + 1 == list.size) binding.imageNextWeapon.gone()
        }
    }
}