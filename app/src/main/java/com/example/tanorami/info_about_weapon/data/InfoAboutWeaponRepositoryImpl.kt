package com.example.tanorami.info_about_weapon.data

import com.example.data.source.weapon.WeaponLocalDataSource
import com.example.data.source.weapon.mapper.toWeaponWithHeroesModel
import com.example.domain.di.DispatcherIo
import com.example.domain.repository.weapon.models.WeaponWithHeroesModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InfoAboutWeaponRepositoryImpl @Inject constructor(
    @DispatcherIo private val ioDispatcher: CoroutineDispatcher,
    private val weaponLocalDataSource: WeaponLocalDataSource,
) : InfoAboutWeaponRepository {
    override suspend fun getWeapon(idWeapon: Int): WeaponWithHeroesModel =
        withContext(ioDispatcher) {
            val weaponInfo = weaponLocalDataSource.getWeaponWithHeroes(idWeapon)
            return@withContext weaponInfo.toWeaponWithHeroesModel()
    }
}