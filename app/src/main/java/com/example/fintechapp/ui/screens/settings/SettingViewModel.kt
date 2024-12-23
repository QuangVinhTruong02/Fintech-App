package com.example.fintechapp.ui.screens.settings

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.request.AdvertisementRequest
import com.example.fintechapp.data.request.CodeScanSpamRequest
import com.example.fintechapp.di.AppModule
import com.example.fintechapp.repository.ConfigurationRepository
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.base.UIState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingViewModel(
    private val configurationRepository: ConfigurationRepository = AppModule.configurationRepository
) : ViewModel() {

    var advertisingTimeInput by mutableStateOf("")
    var codeScanningTimeInput by mutableStateOf("")

    private val _uiState = MutableStateFlow<UIState<Boolean>>(UIState.Empty)
    val uiState: StateFlow<UIState<Boolean>> = _uiState

    private val _switchAdvertisingTimeState = MutableStateFlow(false)
    val switchAdvertisingTimeState: StateFlow<Boolean> = _switchAdvertisingTimeState

    private val _switchCodeScanningTimeState = MutableStateFlow(false)
    val switchCodeScanningTimeState: StateFlow<Boolean> = _switchCodeScanningTimeState

    private val _uiAdvertisementButtonState = MutableStateFlow<UIButtonState>(UIButtonState.Enable)
    val uiAdvertisementButtonState: StateFlow<UIButtonState> = _uiAdvertisementButtonState

    private val _uiCodeScanSpamButtonState = MutableStateFlow<UIButtonState>(UIButtonState.Enable)
    val uiCodeScanSpamButtonState: StateFlow<UIButtonState> = _uiCodeScanSpamButtonState

    suspend fun fetchAdvertisementDuration() {
        val response = configurationRepository.fetchAdvertisementDuration()
        if (response is ResultApi.Success) {
            val advertisement = response.data
            advertisingTimeInput = advertisement?.value.toString()
            _switchAdvertisingTimeState.emit(advertisement?.published ?: false)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    suspend fun fetchCodeScanSpamDuration() {
        val response = configurationRepository.fetchCodeScanSpam()
        if (response is ResultApi.Success) {
            val codeScanSpam = response.data
            codeScanningTimeInput = codeScanSpam?.value.toString()
            _switchCodeScanningTimeState.emit(codeScanSpam?.published ?: false)
        } else {
            ResultApi.Error(Exception(AppLanguage.SOMETHING_WENT_WRONG))
        }
    }

    fun fetchAll() {
        viewModelScope.launch {
            _uiState.emit(UIState.Loading)
            try {
                val result1 = async { fetchAdvertisementDuration() }
                val result2 = async { fetchCodeScanSpamDuration() }

                result1.await()
                result2.await()

                _uiState.emit(UIState.Success(true))
            } catch (e: Exception) {
                _uiState.emit(UIState.Failure(e))
            }
        }
    }

    fun updateAdvertisement(){
        viewModelScope.launch {
            _uiAdvertisementButtonState.emit(UIButtonState.Loading)
            try {
                val advertisementRequest = AdvertisementRequest(
                    value = advertisingTimeInput.toDouble(),
                    isPublished = _switchAdvertisingTimeState.value
                )
                configurationRepository.updateAdvertisementDuration(advertisementRequest)
                _uiAdvertisementButtonState.emit(UIButtonState.Success)
            }catch (e: Exception){}
        }
    }

    fun updateCodeScanSpam(){
        viewModelScope.launch {
            _uiCodeScanSpamButtonState.emit(UIButtonState.Loading)
            try {
                val codeScanSpam = CodeScanSpamRequest(
                    value = codeScanningTimeInput.toDouble(),
                    isPublished = _switchCodeScanningTimeState.value
                )
                configurationRepository.updateCodeScanSpam(codeScanSpam)
                _uiCodeScanSpamButtonState.emit(UIButtonState.Success)
            }catch (e: Exception){}
        }
    }

    fun onValidAdvertisementButton(){
        if(Validation().validateNumber(advertisingTimeInput) == null){
            _uiAdvertisementButtonState.value = UIButtonState.Enable
        }else{
            _uiAdvertisementButtonState.value = UIButtonState.Disable
        }
    }

    fun onValidCodeScanSpamButton(){
        if(Validation().validateNumber(codeScanningTimeInput) == null){
            _uiCodeScanSpamButtonState.value = UIButtonState.Enable
        }else{
            _uiCodeScanSpamButtonState.value = UIButtonState.Disable
        }
    }

    fun onChangeSwitchAdvertisingTime(value: Boolean) {
        _switchAdvertisingTimeState.value = value
    }

    fun onChangeSwitchCodeScanningTime(value: Boolean) {
        _switchCodeScanningTimeState.value = value
    }
}