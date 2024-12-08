package com.example.fintechapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.common.AppShared
import com.example.fintechapp.core.type.PageType
import com.example.fintechapp.di.AppModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val appShared : AppShared = AppModule.appShared) : ViewModel() {
    private val _indexPageType = MutableStateFlow<PageType>(PageType.Agent)
    val indexPageType: StateFlow<PageType> = _indexPageType

    private val _userPhoneLocal = MutableStateFlow<String>("")
    val userPhoneLocal : StateFlow<String> = _userPhoneLocal

    fun onSelectedPage(value: PageType) {
        _indexPageType.value = value
    }

    fun getUserPhoneLocal(){
        viewModelScope.launch {
            _userPhoneLocal.value =appShared.getUserPhone()
        }
    }

    fun onLogout(){
        viewModelScope.launch {
            appShared.logOut()
        }
    }
}