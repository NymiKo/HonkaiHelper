package com.example.honkaihelper.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.example.honkaihelper.base.BaseAdapter
import com.example.honkaihelper.databinding.ItemTeamBinding
import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load

class TeamsAdapter(
    private val actionListener: TeamsAdapterListener
) : BaseAdapter<ItemTeamBinding, TeamHero>(), OnClickListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTeamBinding.inflate(layoutInflater, parent, false)

        binding.cardTeam.setOnClickListener(this)

        return TeamsViewHolder(binding)
    }

    private inner class TeamsViewHolder(private val binding: ItemTeamBinding) :
        BaseViewHolder(binding) {
        override fun bind(model: TeamHero) {
            binding.apply {
                heroAvatarInTeam1.load(model.heroOne.localAvatarPath)
                heroAvatarInTeam2.load(model.heroTwo.localAvatarPath)
                heroAvatarInTeam3.load(model.heroThree.localAvatarPath)
                heroAvatarInTeam4.load(model.heroFour.localAvatarPath)
                heroAvatarInTeam1.backgroundHero(model.heroOne.rarity)
                heroAvatarInTeam2.backgroundHero(model.heroTwo.rarity)
                heroAvatarInTeam3.backgroundHero(model.heroThree.rarity)
                heroAvatarInTeam4.backgroundHero(model.heroFour.rarity)

                heroNameInTeam1.text = model.heroOne.name
                heroNameInTeam2.text = model.heroTwo.name
                heroNameInTeam3.text = model.heroThree.name
                heroNameInTeam4.text = model.heroFour.name

                cardTeam.tag = model.idTeam.toInt()

                textTeamFrom.gone()
                imageProfile.gone()
            }
        }
    }

    override fun onClick(view: View?) {
        val idTeam = view?.tag as Int
        actionListener.onTeamClick(idTeam)
    }
}