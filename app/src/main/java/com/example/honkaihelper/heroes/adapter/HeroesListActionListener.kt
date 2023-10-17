package com.example.honkaihelper.heroes.adapter

import com.example.honkaihelper.heroes.data.model.Hero

interface HeroesListActionListener {
    fun onClick(hero: Hero)
}