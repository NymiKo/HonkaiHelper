package com.example.honkaihelper.teams.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.ItemTeamBinding
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.teams.data.model.TeamHero
import com.example.honkaihelper.utils.gone
import com.example.honkaihelper.utils.invisible
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.loadImageWithRounded
import com.example.honkaihelper.utils.visible

class HeroTeamsListAdapter: RecyclerView.Adapter<HeroTeamsListAdapter.HeroTeamsListViewHolder>() {

    var mTeamsHeroList: List<TeamHero> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroTeamsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTeamBinding.inflate(inflater, parent, false)
        return HeroTeamsListViewHolder(binding)
    }

    override fun getItemCount(): Int = mTeamsHeroList.size

    override fun onBindViewHolder(holder: HeroTeamsListViewHolder, position: Int) {
        val teamHero = mTeamsHeroList[position]
        holder.bind(teamHero)
    }

    inner class HeroTeamsListViewHolder(private val binding: ItemTeamBinding): RecyclerView.ViewHolder(binding.root) {

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

                if (!teamHero.nickname.isNullOrEmpty()) {
                    textTeamFrom.text = textTeamFrom.context.getString(R.string.team_from, teamHero.nickname)
                } else {
                    textTeamFrom.gone()
                }
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