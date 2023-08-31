package com.example.honkaihelper.adapters.create_team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.databinding.ItemHeroCreateTeamBinding
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.utils.load

class CreateTeamAdapter : RecyclerView.Adapter<CreateTeamAdapter.CreateTeamViewHolder>() {

    var mHeroInTeamList = arrayListOf<Hero>()

    fun addHero(hero: Hero) {
        if (mHeroInTeamList.size != 4) mHeroInTeamList.add(hero)
        notifyItemInserted(mHeroInTeamList.size)
    }

    fun removeHero(hero: Hero) {
        val index = mHeroInTeamList.indexOf(hero)
        if (mHeroInTeamList.size != 0) mHeroInTeamList.remove(hero)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateTeamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeroCreateTeamBinding.inflate(inflater, parent, false)
        return CreateTeamViewHolder(binding)
    }

    override fun getItemCount(): Int = mHeroInTeamList.size

    override fun onBindViewHolder(holder: CreateTeamViewHolder, position: Int) {
        val hero = mHeroInTeamList[position]
        holder.bind(hero)
    }

    class CreateTeamViewHolder(private val binding: ItemHeroCreateTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            binding.heroAvatarInCreateTeam.load(hero.avatar)
        }
    }
}