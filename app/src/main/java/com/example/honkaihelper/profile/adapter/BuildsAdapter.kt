package com.example.honkaihelper.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.honkaihelper.R
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.honkaihelper.databinding.ItemBuildsHeroBinding
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.backgroundWeapon
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load

class BuildsAdapter: BaseAdapter<ItemBuildsHeroBinding, BuildHeroWithUser>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBuildsHeroBinding.inflate(layoutInflater, parent, false)

        return BuildsViewHolder(binding)
    }

    private inner class BuildsViewHolder(private val binding: ItemBuildsHeroBinding): BaseViewHolder(binding) {
        override fun bind(model: BuildHeroWithUser) {
            binding.apply {
                imageHeroAvatarInBuildsHero.load(model.hero.localAvatarPath)
                imageHeroAvatarInBuildsHero.backgroundHero(model.hero.rarity)
                textHeroNameInBuildsHero.text = model.hero.name
                imageHeroWeapon.load(model.weapon.image)
                imageHeroWeapon.backgroundWeapon(model.weapon.rarity)
                imageHeroRelicTwoParts.load(model.relicTwoParts.image)
                imageHeroRelicFourParts.load(model.relicFourParts.image)
                imageHeroDecoration.load(model.decoration.image)
                textBuildFrom.gone()
                imageProfile.gone()
            }
        }
    }
}