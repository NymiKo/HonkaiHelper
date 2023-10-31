package com.example.honkaihelper.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.honkaihelper.base_build_hero.data.model.OptimalStatsHero
import com.example.honkaihelper.data.local.contract.RoomContract

@Entity(tableName = RoomContract.tableOptimalStatsHero)
data class OptimalStatsHeroEntity(
    @PrimaryKey(autoGenerate = false)
    val idOptimalStats: Int,
    val attack: String,
    val speed: String,
    val energy: String,
    val hp: String,
    val defence: String,
    val critRate: String,
    val critDamage: String,
    val idHero: Int
) {
    companion object {
        fun toOptimalStatsHeroEntity(optimalStatsHero: OptimalStatsHero) = OptimalStatsHeroEntity(
            idOptimalStats = optimalStatsHero.idOptimalStats,
            attack = optimalStatsHero.attack,
            speed = optimalStatsHero.speed,
            energy = optimalStatsHero.energy,
            hp = optimalStatsHero.hp,
            defence = optimalStatsHero.defence,
            critRate = optimalStatsHero.critRate,
            critDamage = optimalStatsHero.critDamage,
            idHero = optimalStatsHero.idHero
        )
    }

    fun toOptimalStatsHero() = OptimalStatsHero(
        idOptimalStats = idOptimalStats,
        attack = attack,
        speed = speed,
        energy = energy,
        hp = hp,
        defence = defence,
        critRate = critRate,
        critDamage = critDamage,
        idHero = idHero
    )
}
