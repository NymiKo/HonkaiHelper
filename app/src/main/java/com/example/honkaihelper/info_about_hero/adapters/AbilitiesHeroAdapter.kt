package com.example.honkaihelper.info_about_hero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.info_about_hero.data.model.Ability
import com.example.honkaihelper.databinding.ItemAbilityHeroBinding
import com.example.honkaihelper.utils.load

class AbilitiesHeroAdapter: RecyclerView.Adapter<AbilitiesHeroAdapter.AbilitiesViewHolder>() {

    var abilitiesList: List<Ability> = emptyList()
        set(value) {
            field = value
            notifyItemRangeInserted(0, value.size)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAbilityHeroBinding.inflate(layoutInflater, parent, false)

        return AbilitiesViewHolder(binding)
    }

    override fun getItemCount(): Int = abilitiesList.size

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        val ability = abilitiesList[position]
        holder.bind(ability)
    }

    class AbilitiesViewHolder(private val binding: ItemAbilityHeroBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(ability: Ability) {
            binding.apply {
                imageAbility.load(ability.image)
                textTypeAbility.text = ability.type
                textTitleAbility.text = ability.title
                textDescriptionAbility.text = ability.description
            }
        }
    }
}