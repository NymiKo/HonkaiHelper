package com.example.tanorami.profile.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tanorami.databinding.ItemTeamBinding
import com.example.tanorami.teams.data.model.TeamHero
import com.example.tanorami.utils.backgroundHero
import com.example.tanorami.utils.gone
import com.example.tanorami.utils.load

class ProfileTeamsListAdapter :
    RecyclerView.Adapter<ProfileTeamsListAdapter.ProfileTeamsListViewHolder>() {

    var mTeamsList: List<TeamHero> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileTeamsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTeamBinding.inflate(inflater, parent, false)
        return ProfileTeamsListViewHolder(binding)
    }

    override fun getItemCount(): Int = mTeamsList.size

    override fun onBindViewHolder(holder: ProfileTeamsListViewHolder, position: Int) {
        val teamHero = mTeamsList[position]
        holder.bind(teamHero)
    }

    inner class ProfileTeamsListViewHolder(private val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(teamHero: TeamHero) {
            binding.apply {
                heroAvatarInTeam1.load(teamHero.heroOne.localAvatarPath)
                heroAvatarInTeam2.load(teamHero.heroTwo.localAvatarPath)
                heroAvatarInTeam3.load(teamHero.heroThree.localAvatarPath)
                heroAvatarInTeam4.load(teamHero.heroFour.localAvatarPath)
                heroAvatarInTeam1.backgroundHero(teamHero.heroOne.rarity)
                heroAvatarInTeam2.backgroundHero(teamHero.heroTwo.rarity)
                heroAvatarInTeam3.backgroundHero(teamHero.heroThree.rarity)
                heroAvatarInTeam4.backgroundHero(teamHero.heroFour.rarity)

                heroNameInTeam1.text = teamHero.heroOne.name
                heroNameInTeam2.text = teamHero.heroTwo.name
                heroNameInTeam3.text = teamHero.heroThree.name
                heroNameInTeam4.text = teamHero.heroFour.name

                textTeamFrom.gone()
                imageProfile.gone()
            }
        }
    }
}