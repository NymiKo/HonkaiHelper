package com.example.honkaihelper.send_feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.honkaihelper.R
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.send_feedback.data.SendFeedbackRepository
import com.example.honkaihelper.settings.SettingsUIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SendFeedbackViewModel @Inject constructor(
    private val repository: SendFeedbackRepository
): ViewModel() {

    private val _uiState = MutableLiveData<SendFeedbackUIState>()
    val uiState: LiveData<SendFeedbackUIState> = _uiState

    fun sendFeedback(message: String) = viewModelScope.launch {
        _uiState.value = SendFeedbackUIState.LOADING
        when(val result = repository.sendFeedback(message)) {
            is NetworkResult.Error -> {
                errorHandler(result.code)
            }
            is NetworkResult.Success -> {
                _uiState.value = SendFeedbackUIState.SUCCESS
            }
        }
    }

    private fun errorHandler(errorCode: Int) {
        when (errorCode) {
            105 -> _uiState.value = SendFeedbackUIState.ERROR(R.string.check_your_internet_connection)
            106 -> _uiState.value = SendFeedbackUIState.ERROR(R.string.error)
            else -> _uiState.value = SendFeedbackUIState.ERROR(R.string.unknown_error)
        }
    }
}