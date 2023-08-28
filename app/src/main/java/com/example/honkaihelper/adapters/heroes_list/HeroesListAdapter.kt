package com.example.honkaihelper.adapters.heroes_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.R
import com.example.honkaihelper.databinding.ItemHeroViewPagerBinding
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.utils.load

class HeroesListAdapter : RecyclerView.Adapter<HeroesListAdapter.HeroesViewHolder>() {

    var mHeroesList: List<Hero> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeroViewPagerBinding.inflate(inflater, parent, false)

        return HeroesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = mHeroesList[position]
        holder.bind(hero)
    }

    override fun getItemCount(): Int = mHeroesList.size

    class HeroesViewHolder(val binding: ItemHeroViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(hero: Hero) {
                binding.apply {
                    imageHeroAvatar.load(hero.avatar)
                    textHeroName.text = hero.name
                    cardHero.tag = hero

                    if (hero.rarity) {
                        cardHero.strokeColor = ContextCompat.getColor(binding.cardHero.context, R.color.orange)
                    } else {
                        cardHero.strokeColor = ContextCompat.getColor(binding.cardHero.context, R.color.violet)
                    }
                }
            }
        }
}