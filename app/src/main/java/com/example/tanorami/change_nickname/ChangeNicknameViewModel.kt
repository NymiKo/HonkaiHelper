package com.example.tanorami.change_nickname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tanorami.R
import com.example.tanorami.change_nickname.data.ChangeNicknameRepository
import com.example.tanorami.data.NetworkResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeNicknameViewModel @Inject constructor(
    private val repository: ChangeNicknameRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<ChangeNicknameUiState>()
    val uiState: LiveData<ChangeNicknameUiState> = _uiState

    fun changeNickname(oldNickname: String, newNickname: String) = viewModelScope.launch {
        if (checkNickname(newNickname)  && comparisonNickname(oldNickname, newNickname)) {
            _uiState.value = ChangeNicknameUiState.LOADING
            when (val result = repository.changeNickname(newNickname)) {
                is NetworkResult.Error -> {
                    errorHandler(result.code)
                }

                is NetworkResult.Success -> {
                    _uiState.value = ChangeNicknameUiState.SUCCESS
                }
            }
        }
    }

    private fun checkNickname(newNickname: String): Boolean {
        return if (newNickname.isEmpty()) {
            _uiState.value = ChangeNicknameUiState.ERROR(R.string.empty_new_nickname)
            false
        } else true
    }

    private fun comparisonNickname(oldNickname: String, newNickname: String): Boolean {
        return if (newNickname == oldNickname) {
            _uiState.value = ChangeNicknameUiState.ERROR(R.string.already_have_nickname)
            false
        } else true
    }

    private fun errorHandler(errorCode: Int) {
        when (errorCode) {
            105 -> _uiState.value = ChangeNicknameUiState.ERROR(R.string.check_your_internet_connection)
            106 -> _uiState.value = ChangeNicknameUiState.ERROR(R.string.error)
            400 -> _uiState.value = ChangeNicknameUiState.ERROR(R.string.nickname_already_in_use)
            504 -> _uiState.value = ChangeNicknameUiState.ERROR(R.string.error_save_in_server)
            else -> _uiState.value = ChangeNicknameUiState.ERROR(R.string.unknown_error)
        }
    }
}