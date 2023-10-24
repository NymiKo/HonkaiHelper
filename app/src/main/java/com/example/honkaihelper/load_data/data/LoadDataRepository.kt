package com.example.honkaihelper.load_data.data


interface LoadDataRepository {
    suspend fun downloadingData(): Boolean
}