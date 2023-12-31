package com.example.tanorami.createteam.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tanorami.R
import com.example.tanorami.databinding.ItemHeroCreateTeamListBinding
import com.example.tanorami.createteam.data.model.ActiveHeroInTeam
import com.example.tanorami.utils.backgroundHero
import com.example.tanorami.utils.load

class HeroListInCreateTeamAdapter(private val actionListener: HeroListInCreateTeamListener) :
    RecyclerView.Adapter<HeroListInCreateTeamAdapter.HeroListInCreateTeamViewHolder>(), OnClickListener {

    var mHeroList: List<ActiveHeroInTeam> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    fun selectHero(activeHeroInTeam: ActiveHeroInTeam) {
        notifyItemChanged(mHeroList.indexOf(activeHeroInTeam))
    }

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
            binding.heroAvatarInCreateTeamList.load(activeHeroInTeam.hero.localAvatarPath)
            binding.heroNameInCreateTeamList.text = activeHeroInTeam.hero.name
            if (activeHeroInTeam.active) {
                changeStrokeActiveHero(ContextCompat.getColor(binding.cardHeroIconTeam.context, R.color.green), 10, 25F)
            } else {
                changeStrokeActiveHero(ContextCompat.getColor(binding.cardHeroIconTeam.context, R.color.dark_gray), 1, 0F)
            }
            binding.heroAvatarInCreateTeamList.load(activeHeroInTeam.hero.localAvatarPath)
            binding.heroAvatarInCreateTeamList.backgroundHero(activeHeroInTeam.hero.rarity)
            binding.cardHeroIconTeam.tag = activeHeroInTeam
        }

        private fun changeStrokeActiveHero(color: Int, width: Int, radiusCard: Float) {
            binding.cardHeroIconTeam.apply {
                strokeColor = color
                strokeWidth = width
                radius = radiusCard
            }
        }
    }

    override fun onClick(v: View?) {
        val activeHeroInTeam = v?.tag as ActiveHeroInTeam
        actionListener.onClick(activeHeroInTeam)
    }
}