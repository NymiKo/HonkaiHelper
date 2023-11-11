package com.example.honkaihelper.weapon.data

import com.example.honkaihelper.data.local.dao.WeaponDao
import com.example.honkaihelper.weapon.data.model.FullWeaponInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeaponInfoRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val weaponDao: WeaponDao
) : WeaponInfoRepository {
    override suspend fun getWeapon(idWeapon: Int): FullWeaponInfo = withContext(ioDispatcher) {
        val weaponInfo = weaponDao.getWeaponWithPath(idWeapon)
        return@withContext FullWeaponInfo(
            weaponInfo.weapon.toWeapon(),
            weaponInfo.path.toPath()
        )
    }
}