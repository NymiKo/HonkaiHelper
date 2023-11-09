package com.example.honkaihelper.weapon.data

import com.example.honkaihelper.base_build_hero.data.model.Weapon
import com.example.honkaihelper.data.local.dao.WeaponDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeaponInfoRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val weaponDao: WeaponDao
): WeaponInfoRepository {
    override suspend fun getWeapon(idWeapon: Int): Weapon = withContext(ioDispatcher) {
        return@withContext weaponDao.getWeapon(idWeapon).toWeapon()
    }
}