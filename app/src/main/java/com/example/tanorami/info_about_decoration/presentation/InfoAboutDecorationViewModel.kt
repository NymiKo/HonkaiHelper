package com.example.tanorami.info_about_decoration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.info_about_decoration.data.InfoAboutDecorationRepository
import com.example.tanorami.info_about_hero.data.model.Decoration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoAboutDecorationViewModel @Inject constructor(
    private val repository: InfoAboutDecorationRepository
): ViewModel() {

    private val _decoration = MutableStateFlow<Decoration?>(null)
    val decoration: StateFlow<Decoration?> = _decoration

    fun getDecoration(idDecoration: Int) = viewModelScope.launch {
        _decoration.emit(repository.getDecoration(idDecoration))
    }

}