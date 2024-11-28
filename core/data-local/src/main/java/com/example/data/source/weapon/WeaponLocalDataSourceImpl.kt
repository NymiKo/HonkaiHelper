package com.example.data.source.weapon

import com.example.data.db.dao.WeaponDao
import com.example.data.db.entity.WeaponEntity
import com.example.data.db.models.weapon.WeaponWithPathRelation
import com.example.domain.di.DispatcherIo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeaponLocalDataSourceImpl @Inject constructor(
    private val weaponDao: WeaponDao,
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
) : WeaponLocalDataSource {
    override suspend fun getWeapons(): List<WeaponEntity> {
        return withContext(ioDispatcher) {
            weaponDao.getWeapons()
        }
    }

    override suspend fun getWeaponWithPath(idWeapon: Int): WeaponWithPathRelation {
        return withContext(ioDispatcher) {
            weaponDao.getWeaponWithPath(idWeapon)
        }
    }

    override suspend fun getWeapon(idWeapon: Int): WeaponEntity {
        return withContext(ioDispatcher) {
            weaponDao.getWeapon(idWeapon)
        }
    }

    override suspend fun getWeaponByPath(path: Int): List<WeaponEntity> {
        return withContext(ioDispatcher) {
            weaponDao.getWeaponByPath(path)
        }
    }

    override suspend fun insertWeapons(weapons: List<WeaponEntity>) {
        return withContext(ioDispatcher) {
            weaponDao.insertWeapons(weapons)
        }
    }

}