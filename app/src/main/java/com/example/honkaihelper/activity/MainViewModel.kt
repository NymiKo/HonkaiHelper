package com.example.honkaihelper.activity

import androidx.lifecycle.ViewModel
import com.example.honkaihelper.activity.data.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
}