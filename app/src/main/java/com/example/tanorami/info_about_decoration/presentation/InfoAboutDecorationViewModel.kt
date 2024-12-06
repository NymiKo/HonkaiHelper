package com.example.tanorami.info_about_decoration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.info_about_decoration.data.InfoAboutDecorationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoAboutDecorationViewModel @Inject constructor(
    private val repository: InfoAboutDecorationRepository
): ViewModel() {

    private val _decoration =
        MutableStateFlow<com.example.common.DecorationModel?>(null)
    val decoration: StateFlow<com.example.common.DecorationModel?> = _decoration

    fun getDecoration(idDecoration: Int) = viewModelScope.launch {
        _decoration.emit(repository.getDecoration(idDecoration))
    }

}