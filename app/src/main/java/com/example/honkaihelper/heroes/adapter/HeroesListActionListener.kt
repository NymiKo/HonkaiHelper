package com.example.honkaihelper.heroes.adapter

import com.example.honkaihelper.heroes.data.model.Hero
import com.google.android.material.card.MaterialCardView

interface HeroesListActionListener {
    fun onClick(hero: Hero, view: MaterialCardView)
}