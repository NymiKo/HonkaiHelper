package com.example.honkaihelper.setupteam.adapter

interface SetupTeamListener {
    fun onWeaponClick(heroPath: Int, idItem: Int)
    fun onRelicClick(idItem: Int)
    fun onDecorationClick(idItem: Int)
}