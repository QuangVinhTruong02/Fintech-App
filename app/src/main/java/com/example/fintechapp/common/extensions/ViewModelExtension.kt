package com.example.fintechapp.common.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.ui.base.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun <T> ViewModel.fetchData(
    uiState: MutableStateFlow<UIState<T>>,
    fetchBlock: suspend () -> ResultApi<T?>,
    onSuccess: (T) -> Unit = {}
){
    viewModelScope.launch {
        try {
            uiState.emit(UIState.Loading)
            val response = fetchBlock()
            if(response is ResultApi.Success){
                if(response.data != null){
                    val data = response.data
                    uiState.emit(UIState.Success(data))
                    onSuccess(data)
                }else{
                    uiState.emit(UIState.Empty)
                }
            }else{
                uiState.emit(UIState.Failure(Exception("Failed to fetch data")))
            }
        }catch (e: Exception){
            uiState.emit(UIState.Failure(e))
        }
    }
}
