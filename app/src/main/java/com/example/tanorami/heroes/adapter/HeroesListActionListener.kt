package com.example.tanorami.heroes.adapter

import com.example.tanorami.heroes.data.model.Hero
import com.google.android.material.card.MaterialCardView

interface HeroesListActionListener {
    fun onClick(hero: Hero, view: MaterialCardView)
}