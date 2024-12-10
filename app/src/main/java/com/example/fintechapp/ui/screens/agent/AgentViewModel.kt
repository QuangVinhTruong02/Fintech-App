package com.example.fintechapp.ui.screens.agent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.di.AppModule
import com.example.fintechapp.repository.AgencyRepository
import com.example.fintechapp.ui.base.UICheckState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class AgentViewModel(
    private val agencyRepository: AgencyRepository = AppModule.agencyRepository,
//    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var page = 1
    private var pageSize = 10
    private var count = 10
    private var totalCount = 0;

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorText = MutableStateFlow<String>("")
    val errorText: StateFlow<String> = _errorText

    private val _agencyList = MutableStateFlow<List<AgencyResponse>>(emptyList())
    val agencyList: StateFlow<List<AgencyResponse>> = _agencyList

    private val _searchTextInput = MutableStateFlow<String>("")
    val searchTextInput: StateFlow<String> = _searchTextInput

    private val _selectedAgents = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val selectedAgents: StateFlow<Map<Int, Boolean>> = _selectedAgents

    private val _preHasDataToFetch = MutableStateFlow<Boolean>(false)
    val preHasDataToFetch: StateFlow<Boolean> = _preHasDataToFetch

    private val _nextHasDataToFetch = MutableStateFlow<Boolean>(true)
    val nextHasDataToFetch: StateFlow<Boolean> = _nextHasDataToFetch

    private val _uiAllCheckState = MutableStateFlow<UICheckState>(UICheckState.Unchecked)
    val uiAllCheckState: StateFlow<UICheckState> = _uiAllCheckState

    private val _showDialog = MutableStateFlow<Boolean>(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private val _hasNewData = MutableStateFlow<Boolean?>(null)
    val hasNewData : StateFlow<Boolean?> = _hasNewData

    private var _selectedAgency = MutableStateFlow<AgencyResponse?>(null)
    val selectedAgency : StateFlow<AgencyResponse?> = _selectedAgency

    init {
        fetchAgencies(_searchTextInput.value)
        handleSearchInput()
        _hasNewData
    }

    private fun handleSearchInput() {
        viewModelScope.launch {
            _searchTextInput
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    fetchAgencies(query)
                }
        }
    }

    fun fetchAgencies(query: String) {
        viewModelScope.launch {
            _isLoading.emit(true)
            try {
                val response =
                    agencyRepository.fetchAgencies(page, pageSize, query)
                if (response is ResultApi.Success) {
                    _agencyList.emit(response.data!!.data.agentList)
                    totalCount = response.data.data.count
                    setValueForHasData()
                }
            } catch (e: Exception) {
                _errorText.emit(e.toString())
            }

            _isLoading.emit(false)
        }
    }


    private fun setValueForHasData() {
        _nextHasDataToFetch.value = count < totalCount
        _preHasDataToFetch.value = page != 1
    }

    fun setValueShowDialog(value: Boolean) {
        _showDialog.value = value
    }

    fun onQueryChanged(newQuery: String) {
        _searchTextInput.value = newQuery
    }

    fun onDeleteAgencyById() {
        viewModelScope.launch {
            val response = agencyRepository.deleteAgencyById(selectedAgency.value!!.id)
            if (response is ResultApi.Success) {
                fetchAgencies("")
            }
        }
    }

    fun setSelectedAgency(agencyResponse: AgencyResponse) {
        _selectedAgency.value = agencyResponse
    }

    fun onPageIncrement() {
        if (count < totalCount) {
            page += 1
            count += 10
            setValueForHasData()
            fetchAgencies("")
        }
    }

    fun onRemoveInput() {
        _searchTextInput.value = ""
    }

    fun onPageDecrement() {
        if (page > 1) {
            page -= 1
            count -= 10
            setValueForHasData()
            fetchAgencies("")
        }
    }

    fun onToggleAgentSelection(id: Int, isChecked: Boolean) {
        _selectedAgents.update { selected ->
            selected.toMutableMap().apply {
                put(id, isChecked)
            }
        }
        onCheckAllSelected()
    }

    private fun onCheckAllSelected() {
        val hasAllSelected = _selectedAgents.value.all { it.value }
        val hasTrue = _selectedAgents.value.any { it.value }
        if (!hasAllSelected && hasTrue) {
            _uiAllCheckState.value = UICheckState.Subtracted
        } else if (hasAllSelected) {
            _uiAllCheckState.value = UICheckState.Checked
        } else {
            _uiAllCheckState.value = UICheckState.Unchecked
        }
    }

    fun onToggleAllAgentsSelection() {
        val newCheckState = when (_uiAllCheckState.value) {
            UICheckState.Unchecked -> UICheckState.Checked
            UICheckState.Checked, UICheckState.Subtracted -> UICheckState.Unchecked
        }

        val updatedSelection: Map<Int, Boolean> = _agencyList.value.associate {
            it.id to (newCheckState == UICheckState.Checked)
        }

        _selectedAgents.value = updatedSelection
        _uiAllCheckState.value = newCheckState
    }
}