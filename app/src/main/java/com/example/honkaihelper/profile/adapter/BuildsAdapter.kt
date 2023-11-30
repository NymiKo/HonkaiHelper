package com.example.honkaihelper.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.builds_hero_from_users.data.model.BuildHeroWithUser
import com.example.honkaihelper.databinding.ItemBuildsHeroBinding
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.backgroundWeapon
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load

class BuildsAdapter(
    private val actionListener: BuildsAdapterListener
): BaseAdapter<ItemBuildsHeroBinding, BuildHeroWithUser>(), OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemBuildsHeroBinding.inflate(layoutInflater, parent, false)

        binding.cardBuildsHero.setOnClickListener(this)

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

                cardBuildsHero.tag = model.idBuild
            }
        }
    }

    override fun onClick(view: View?) {
        val idBuild = view?.tag as Int
        actionListener.onClickBuild(idBuild)
    }
}