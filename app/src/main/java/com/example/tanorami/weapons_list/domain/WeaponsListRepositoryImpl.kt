package com.example.tanorami.weapons_list.domain

import com.example.core.di.IODispatcher
import com.example.data.local.dao.WeaponDao
import com.example.domain.repository.weapon.WeaponModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeaponsListRepositoryImpl @Inject constructor(
    private val weaponDao: WeaponDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : WeaponsListRepository {
    override suspend fun getWeaponsList(): List<WeaponModel> =
        withContext(ioDispatcher) {
        return@withContext weaponDao.getWeapons().map { weaponEntity -> weaponEntity.toWeapon() }
            .sortedBy { weapon -> weapon.name }
    }
}