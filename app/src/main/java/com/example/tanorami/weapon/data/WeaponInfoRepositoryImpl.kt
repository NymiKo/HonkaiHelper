package com.example.tanorami.weapon.data

import com.example.tanorami.data.local.dao.WeaponDao
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.weapon.data.model.FullWeaponInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeaponInfoRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
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