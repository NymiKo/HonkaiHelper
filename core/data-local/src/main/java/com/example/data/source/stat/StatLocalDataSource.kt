package com.example.data.source.stat

import com.example.data.db.entity.StatEntity

interface StatLocalDataSource {
    suspend fun getStatsList(): List<StatEntity>
    suspend fun insertHeroesList(statsList: List<StatEntity>)
}