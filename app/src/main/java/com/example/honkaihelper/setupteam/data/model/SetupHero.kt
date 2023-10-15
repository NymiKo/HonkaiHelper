package com.example.honkaihelper.setupteam.data.model

import com.example.honkaihelper.equipment.data.model.Equipment
import com.example.honkaihelper.heroes.data.model.Hero

data class SetupHero(val hero: Hero, var level: Int?, var eidolon: Int = 0, var weapon: Equipment?) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SetupHero

        if (hero != other.hero) return false
        if (level != other.level) return false
        if (eidolon != other.eidolon) return false
        if (weapon != other.weapon) return false

        return true
    }

    override fun hashCode(): Int {
        var result = hero.hashCode()
        result = 31 * result + (level ?: 0)
        result = 31 * result + eidolon
        result = 31 * result + (weapon?.hashCode() ?: 0)
        return result
    }

}
