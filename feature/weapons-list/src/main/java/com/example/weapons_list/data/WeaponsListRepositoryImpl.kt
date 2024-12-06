package com.example.weapons_list.data

import com.example.common.WeaponModel
import com.example.data.source.weapon.WeaponLocalDataSource
import com.example.data.source.weapon.mapper.toWeaponModel
import com.example.weapons_list.domain.WeaponsListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class WeaponsListRepositoryImpl @Inject constructor(
    private val weaponLocalDataSource: WeaponLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : WeaponsListRepository {
    override suspend fun getWeaponsList(): List<WeaponModel> = withContext(ioDispatcher) {
        return@withContext weaponLocalDataSource.getWeapons()
            .map { weaponEntity -> weaponEntity.toWeaponModel() }
            .sortedBy { weapon -> weapon.name }
    }
}