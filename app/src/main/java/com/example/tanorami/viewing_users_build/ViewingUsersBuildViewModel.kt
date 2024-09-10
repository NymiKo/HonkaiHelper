package com.example.tanorami.viewing_users_build

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.data.NetworkResult
import com.example.tanorami.viewing_users_build.data.ViewingUsersBuildRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewingUsersBuildViewModel @Inject constructor(
    private val repository: ViewingUsersBuildRepository
): ViewModel() {

    private val _uiState = MutableLiveData<ViewingUsersBuildUiState>(ViewingUsersBuildUiState.LOADING)
    val uiState: LiveData<ViewingUsersBuildUiState> = _uiState

    fun getHeroBuild(idBuild: Long, uid: String?) = viewModelScope.launch {
        ViewingUsersBuildUiState.LOADING
        val result = if (uid == "") {
            repository.getHeroBuildByID(idBuild)
        } else {
            repository.getHeroBuildByUID(uid!!)
        }

        when(result) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
            }
            is NetworkResult.Success -> {
                _uiState.value = ViewingUsersBuildUiState.SUCCESS(result.data)
            }
        }
    }

    private fun errorHandler(errorCode: Int) {
        when (errorCode) {
            105 -> _uiState.value =
                ViewingUsersBuildUiState.ERROR(R.string.check_your_internet_connection)
            else -> _uiState.value = ViewingUsersBuildUiState.ERROR(R.string.error)
        }
    }
}