package com.example.honkaihelper.equipment.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.data.local.dao.DecorationDao
import com.example.honkaihelper.data.local.dao.RelicDao
import com.example.honkaihelper.data.local.dao.WeaponDao
import com.example.honkaihelper.equipment.data.model.Equipment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val weaponDao: WeaponDao,
    private val relicDao: RelicDao,
    private val decorationDao: DecorationDao
): EquipmentRepository {
    override suspend fun getWeapons(path: Int): List<Equipment> = withContext(ioDispatcher) {
        weaponDao.getWeaponByPath(path).map {
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