package com.example.tanorami.createteam.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tanorami.data.local.models.hero.HeroWithNameAvatarRarity
import com.example.tanorami.databinding.ItemHeroCreateTeamBinding
import com.example.tanorami.utils.load

class CreateTeamAdapter : RecyclerView.Adapter<CreateTeamAdapter.CreateTeamViewHolder>() {

    var mHeroListInTeam = emptyList<HeroWithNameAvatarRarity>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateTeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeroCreateTeamBinding.inflate(inflater, parent, false)
        return CreateTeamViewHolder(binding)
    }

    override fun getItemCount(): Int = mHeroListInTeam.size

    override fun onBindViewHolder(holder: CreateTeamViewHolder, position: Int) {
        val hero = mHeroListInTeam[position]
        holder.bind(hero)
    }

    class CreateTeamViewHolder(private val binding: ItemHeroCreateTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: HeroWithNameAvatarRarity) {
            binding.heroAvatarInCreateTeam.load(hero.localAvatarPath)
        }
    }
}