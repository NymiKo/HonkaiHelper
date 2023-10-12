package com.example.honkaihelper.equipment.data

import com.example.honkaihelper.equipment.data.model.Equipment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EquipmentService {
    @GET("/weaponByPath.php")
    suspend fun getWeapon(@Query("path") path: Int): Response<List<Equipment>>

    @GET("/relic.php")
    suspend fun getRelic(): Response<List<Equipment>>

    @GET("/decoration.php")
    suspend fun getDecoration(): Response<List<Equipment>>
}