package com.example.honkaihelper.profile.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.ItemTeamBinding
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.utils.loadImageWithRounded

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
                backgroundHero(heroAvatarInTeam1, teamHero.heroOne)
                backgroundHero(heroAvatarInTeam2, teamHero.heroTwo)
                backgroundHero(heroAvatarInTeam3, teamHero.heroThree)
                backgroundHero(heroAvatarInTeam4, teamHero.heroFour)

                heroNameInTeam1.text = teamHero.heroOne.name
                heroNameInTeam2.text = teamHero.heroTwo.name
                heroNameInTeam3.text = teamHero.heroThree.name
                heroNameInTeam4.text = teamHero.heroFour.name
            }
        }
    }

    private fun backgroundHero(view: ImageView, hero: Hero) {
        view.loadImageWithRounded(hero.avatar)
        if (hero.rarity) {
            view.background = ContextCompat.getDrawable(view.context, R.color.orange)
        } else {
            view.background = ContextCompat.getDrawable(view.context, R.color.violet)
        }
    }

}