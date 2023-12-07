package com.example.tanorami.load_data.data


interface LoadDataRepository {
    suspend fun downloadingData(): Boolean
}