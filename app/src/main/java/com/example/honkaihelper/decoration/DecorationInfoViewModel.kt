package com.example.honkaihelper.decoration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.decoration.data.DecorationInfoRepository
import com.example.honkaihelper.info_about_hero.data.model.Decoration
import kotlinx.coroutines.launch
import javax.inject.Inject

class DecorationInfoViewModel @Inject constructor(
    private val repository: DecorationInfoRepository
): ViewModel() {

    private val _decoration = MutableLiveData<Decoration>()
    val decoration: LiveData<Decoration> = _decoration

    fun getDecoration(idDecoration: Int) = viewModelScope.launch {
        _decoration.value = repository.getDecoration(idDecoration)
    }

}