package com.example.honkaihelper.base_build_hero.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.databinding.ItemWeaponBaseBuildHeroBinding
import com.example.honkaihelper.utils.backgroundRarity
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load

class WeaponsAdapter(
    private val actionListener: ItemClickListener
) : BaseAdapter<ItemWeaponBaseBuildHeroBinding, Weapon>(), OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemWeaponBaseBuildHeroBinding.inflate(layoutInflater, parent, false)

        binding.imageWeaponBaseBuildHero.setOnClickListener(this)

        return WeaponViewHolder(binding)
    }

    private inner class WeaponViewHolder(private val binding: ItemWeaponBaseBuildHeroBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: Weapon) {
            ViewCompat.setTransitionName(
                binding.imageWeaponBaseBuildHero,
                binding.imageWeaponBaseBuildHero.context.getString(
                    R.string.base_build_weapon_transition_name, model.idWeapon
                )
            )
            binding.imageWeaponBaseBuildHero.load(model.image)
            binding.cardWeaponIcon.backgroundRarity(model.rarity)
            binding.imageWeaponBaseBuildHero.tag = model.idWeapon
            if (adapterPosition + 1 == list.size) binding.imageNextWeapon.gone()
        }
    }

    override fun onClick(v: View?) {
        val itemId = v?.tag as Int
        actionListener.onItemClick(itemId, v as ImageView)
    }
}