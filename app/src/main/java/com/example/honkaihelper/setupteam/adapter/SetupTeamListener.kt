package com.example.honkaihelper.setupteam.adapter

import com.example.honkaihelper.setupteam.data.model.SetupHero

interface SetupTeamListener {
    fun onWeaponClick(heroPath: Int, idItem: Int)
    fun onRelicClick(idItem: Int)
    fun onDecorationClick(idItem: Int)
}