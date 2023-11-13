package com.example.honkaihelper.profile.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.databinding.ItemTeamBinding
import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.utils.backgroundHero
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.load

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
                heroAvatarInTeam1.load(teamHero.heroOne.avatar)
                heroAvatarInTeam2.load(teamHero.heroTwo.avatar)
                heroAvatarInTeam3.load(teamHero.heroThree.avatar)
                heroAvatarInTeam4.load(teamHero.heroFour.avatar)
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