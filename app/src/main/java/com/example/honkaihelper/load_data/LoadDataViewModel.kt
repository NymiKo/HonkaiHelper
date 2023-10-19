package com.example.honkaihelper.load_data

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.load_data.data.LoadDataRepository
import javax.inject.Inject

class LoadDataViewModel @Inject constructor(
    private val repository: LoadDataRepository
): ViewModel(){
}