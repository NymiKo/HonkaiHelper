package com.example.honkaihelper.base_build_hero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.databinding.ItemWeaponBaseBuildHeroBinding
import com.example.honkaihelper.utils.backgroundEquipment
import com.example.honkaihelper.utils.backgroundRarity
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load

class WeaponsAdapter : BaseAdapter<ItemWeaponBaseBuildHeroBinding, Weapon>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemWeaponBaseBuildHeroBinding.inflate(layoutInflater, parent, false)

        return WeaponViewHolder(binding)
    }

    private inner class WeaponViewHolder(private val binding: ItemWeaponBaseBuildHeroBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: Weapon) {
            binding.imageWeaponBaseBuildHero.load(model.image)
            binding.imageWeaponBaseBuildHero.backgroundRarity(model.rarity)
            if(adapterPosition + 1 == list.size) binding.imageNextWeapon.gone()
        }
    }
}