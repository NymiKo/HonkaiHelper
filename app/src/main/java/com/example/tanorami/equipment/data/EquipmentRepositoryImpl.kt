package com.example.tanorami.equipment.data

import com.example.tanorami.data.local.dao.DecorationDao
import com.example.tanorami.data.local.dao.RelicDao
import com.example.tanorami.data.local.dao.WeaponDao
import com.example.tanorami.di.IODispatcher
import com.example.tanorami.equipment.data.model.Equipment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao
) : EquipmentRepository {
    override suspend fun getWeapons(path: Int): List<Equipment> = withContext(ioDispatcher) {
        weaponDao.getWeaponByPath(path).sortedByDescending { it.rarity }.map {
            Equipment(it.idWeapon, it.image, it.rarity.toByte())
        }
    }

    override suspend fun getRelics(): List<Equipment> = withContext(ioDispatcher) {
        relicDao.getRelics().map {
            Equipment(it.idRelic, it.image)
        }
    }

    override suspend fun getDecorations(): List<Equipment> = withContext(ioDispatcher) {
        decorationDao.getDecorations().map {
            Equipment(it.idDecoration, it.image)
        }
    }
}