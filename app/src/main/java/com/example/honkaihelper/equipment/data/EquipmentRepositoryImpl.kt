package com.example.honkaihelper.equipment.data

import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.data.handleApi
import com.example.honkaihelper.equipment.data.model.Equipment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EquipmentRepositoryImpl @Inject constructor(
    private val equipmentService: EquipmentService,
    private val ioDispatcher: CoroutineDispatcher
): EquipmentRepository {
    override suspend fun getWeapon(path: Int): NetworkResult<List<Equipment>> {
        return withContext(ioDispatcher) {
            handleApi {
                equipmentService.getWeapon(path)
            }
        }
    }

    override suspend fun getRelic(): NetworkResult<List<Equipment>> {
        return withContext(ioDispatcher) {
            handleApi {
                equipmentService.getRelic()
            }
        }
    }

    override suspend fun getDecoration(): NetworkResult<List<Equipment>> {
        return withContext(ioDispatcher) {
            handleApi {
                equipmentService.getDecoration()
            }
        }
    }
}