package com.example.tanorami.heroes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tanorami.R
import com.example.tanorami.databinding.ItemHeroBinding
import com.example.tanorami.heroes.data.model.Hero
import com.example.tanorami.utils.load
import com.google.android.material.card.MaterialCardView

class HeroesListAdapter(
    private val actionListener: HeroesListActionListener
) : RecyclerView.Adapter<HeroesListAdapter.HeroesViewHolder>(), OnClickListener {

    var mHeroesList: List<Hero> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeroBinding.inflate(inflater, parent, false)

        binding.cardHeroIcon.setOnClickListener(this)

        return HeroesViewHolder(binding)
    }

    override fun getItemCount(): Int = mHeroesList.size

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val hero = mHeroesList[position]
        holder.bind(hero)
    }

    class HeroesViewHolder(private val binding: ItemHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hero: Hero) {
            binding.imageHeroAvatar.load(hero.avatar)
            if (hero.rarity) {
                binding.cardHeroIcon.strokeColor =
                    ContextCompat.getColor(binding.cardHeroIcon.context, R.color.orange)
            } else {
                binding.cardHeroIcon.strokeColor =
                    ContextCompat.getColor(binding.cardHeroIcon.context, R.color.violet)
            }
            binding.textHeroName.text = hero.name
            binding.cardHeroIcon.tag = hero
        }
    }

    override fun onClick(view: View?) {
        val hero = view?.tag as Hero
        actionListener.onClick(hero, view as MaterialCardView)
    }
}