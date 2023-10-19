package com.example.honkaihelper.load_data.data

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoadDataRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val loadDataService: LoadDataService
) : LoadDataRepository {


}