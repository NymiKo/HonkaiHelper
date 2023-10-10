package com.example.honkaihelper.setupteam.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.ItemSetupTeamBinding
import com.example.honkaihelper.heroes.data.model.Hero
import com.example.honkaihelper.utils.load

class SetupTeamAdapter : RecyclerView.Adapter<SetupTeamAdapter.SetupTeamViewHolder>() {

    var heroesList: List<Hero> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetupTeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSetupTeamBinding.inflate(layoutInflater, parent, false)
        return SetupTeamViewHolder(binding)
    }

    override fun getItemCount(): Int = heroesList.size

    override fun onBindViewHolder(holder: SetupTeamViewHolder, position: Int) {
        val hero = heroesList[position]
        holder.bind(hero)
    }

    class SetupTeamViewHolder(private val binding: ItemSetupTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Hero) {
            binding.apply {
                imageHeroAvatarInSetupTeam.load(hero.avatar)
                textHeroNameInSetupTeam.text = hero.name
                backgroundHero(hero.rarity)

                ArrayAdapter.createFromResource(binding.spinnerHeroLevel.context, R.array.hero_level, android.R.layout.simple_spinner_item).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinnerHeroLevel.adapter = adapter
                }

                ArrayAdapter.createFromResource(binding.spinnerHeroEidolon.context, R.array.hero_eidolon, android.R.layout.simple_spinner_item).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinnerHeroEidolon.adapter = adapter
                }
            }
        }

        private fun backgroundHero(rarity: Boolean) {
            if (rarity) {
                binding.imageHeroAvatarInSetupTeam.background = ContextCompat.getDrawable(binding.imageHeroAvatarInSetupTeam.context, R.color.orange)
            } else {
                binding.imageHeroAvatarInSetupTeam.background = ContextCompat.getDrawable(binding.imageHeroAvatarInSetupTeam.context, R.color.violet)
            }
        }
    }
}