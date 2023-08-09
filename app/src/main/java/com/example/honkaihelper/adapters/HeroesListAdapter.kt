package com.example.honkaihelper.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.honkaihelper.databinding.ItemHeroBinding
import com.example.honkaihelper.models.Hero
import com.example.honkaihelper.utils.load

class HeroesListAdapter: RecyclerView.Adapter<HeroesListAdapter.HeroesViewHolder>() {

    var mHeroesList: List<Hero> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeroBinding.inflate(inflater, parent, false)

        return HeroesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = mHeroesList[position]
        with(holder.binding) {
            imageHeroAvatar.load(hero.avatar)
            textHeroName.text = hero.name
            cardHero.tag = hero
        }
    }

    override fun getItemCount(): Int = mHeroesList.size

    class HeroesViewHolder(val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root)
}