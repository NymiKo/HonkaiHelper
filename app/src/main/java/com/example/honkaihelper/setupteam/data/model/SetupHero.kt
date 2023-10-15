package com.example.honkaihelper.setupteam.data.model

import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.heroes.data.model.Hero

data class SetupHero(val hero: Hero, var level: Int?, var eidolon: Int = 0, var weapon: Equipment?)
