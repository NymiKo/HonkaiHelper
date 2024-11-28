package com.example.tanorami.info_about_weapon.data

import com.example.data.db.dao.WeaponDao
import com.example.domain.di.DispatcherIo
import com.example.tanorami.info_about_weapon.data.model.FullInfoAboutWeapon
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoAboutWeaponRepositoryImpl @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val weaponDao: WeaponDao,
) : InfoAboutWeaponRepository {
    override suspend fun getWeapon(idWeapon: Int): FullInfoAboutWeapon = withContext(ioDispatcher) {
        val weaponInfo = weaponDao.getWeaponWithPath(idWeapon)
        return@withContext FullInfoAboutWeapon(
            weaponInfo.weapon.toWeapon(),
            weaponInfo.path.toPath()
        )
    }
}