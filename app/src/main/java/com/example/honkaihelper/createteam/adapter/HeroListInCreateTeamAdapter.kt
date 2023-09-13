package com.example.honkaihelper.createteam.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.ItemHeroCreateTeamListBinding
import com.example.honkaihelper.models.ActiveHeroInTeam
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.utils.load
import com.example.honkaihelper.utils.loadImageWithRounded

class HeroListInCreateTeamAdapter(private val actionListener: HeroListInCreateTeamListener) :
    RecyclerView.Adapter<HeroListInCreateTeamAdapter.HeroListInCreateTeamViewHolder>(), OnClickListener {

    var mHeroList: List<ActiveHeroInTeam> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    private val mActiveHeroesList = ArrayList<ActiveHeroInTeam>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeroListInCreateTeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeroCreateTeamListBinding.inflate(inflater, parent, false)

        binding.cardHeroIconTeam.setOnClickListener(this)

        return HeroListInCreateTeamViewHolder(binding)
    }

    override fun getItemCount(): Int = mHeroList.size

    override fun onBindViewHolder(holder: HeroListInCreateTeamViewHolder, position: Int) {
        val hero = mHeroList[position]
        holder.bind(hero)
    }

    class HeroListInCreateTeamViewHolder(private val binding: ItemHeroCreateTeamListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(activeHeroInTeam: ActiveHeroInTeam) {
            binding.heroAvatarInCreateTeamList.load(activeHeroInTeam.hero.avatar)
            binding.heroNameInCreateTeamList.text = activeHeroInTeam.hero.name
            if (activeHeroInTeam.active) {
                changeStrokeActiveHero(ContextCompat.getColor(binding.cardHeroIconTeam.context, R.color.green), 10, 25F)
            } else {
                changeStrokeActiveHero(ContextCompat.getColor(binding.cardHeroIconTeam.context, R.color.dark_gray), 1, 0F)
            }
            backgroundHero(activeHeroInTeam.hero)
            binding.cardHeroIconTeam.tag = activeHeroInTeam
        }

        private fun changeStrokeActiveHero(color: Int, width: Int, radiusCard: Float) {
            binding.cardHeroIconTeam.apply {
                strokeColor = color
                strokeWidth = width
                radius = radiusCard
            }
        }

        private fun backgroundHero(hero: Hero) {
            binding.heroAvatarInCreateTeamList.loadImageWithRounded(hero.avatar)
            if (hero.rarity) {
                binding.heroAvatarInCreateTeamList.background = ContextCompat.getDrawable(binding.heroAvatarInCreateTeamList.context, R.color.orange)
            } else {
                binding.heroAvatarInCreateTeamList.background = ContextCompat.getDrawable(binding.heroAvatarInCreateTeamList.context, R.color.violet)
            }
        }
    }

    override fun onClick(v: View?) {
        val activeHeroInTeam = v?.tag as ActiveHeroInTeam
        actionListener.onClick(activeHeroInTeam)
        if (!activeHeroInTeam.active && mActiveHeroesList.size != 4) {
            activeHeroInTeam.active = true
            mActiveHeroesList.add(activeHeroInTeam)
        } else {
            activeHeroInTeam.active = false
            mActiveHeroesList.remove(activeHeroInTeam)
        }
        notifyItemChanged(mHeroList.indexOf(activeHeroInTeam))
    }
}