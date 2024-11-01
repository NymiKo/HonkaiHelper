package com.example.tanorami.weapons_list.data

import com.example.tanorami.core.data.local.dao.WeaponDao
import com.example.tanorami.core.di.IODispatcher
import com.example.tanorami.weapons_list.data.models.Weapon
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeaponsListRepositoryImpl @Inject constructor(
    private val weaponDao: WeaponDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : WeaponsListRepository {
    override suspend fun getWeaponsList(): List<Weapon> = withContext(ioDispatcher) {
        return@withContext weaponDao.getWeapons().map { weaponEntity -> weaponEntity.toWeapon() }
            .sortedBy { weapon -> weapon.name }
    }
}