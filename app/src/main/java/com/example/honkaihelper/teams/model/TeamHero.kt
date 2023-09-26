package com.example.honkaihelper.teams.model

import com.example.honkaihelper.heroes.data.model.Hero

data class TeamHero(
    val idTeam: Long,
    val heroOne: Hero,
    val heroTwo: Hero,
    val heroThree: Hero,
    val heroFour: Hero,
    val idUser: Long
)
