package com.example.fintechapp.ui.screens.detail_agent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.common.extensions.fetchData
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.request.AgencyRequest
import com.example.fintechapp.data.request.LocationRequest
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.data.response.ClientResponse
import com.example.fintechapp.data.response.ClientsResponse
import com.example.fintechapp.data.response.DistrictResponse
import com.example.fintechapp.data.response.LocationResponse
import com.example.fintechapp.data.response.WardResponse
import com.example.fintechapp.di.AppModule
import com.example.fintechapp.repository.AgencyRepository
import com.example.fintechapp.repository.LocationRepository
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.base.UIState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class DetailAgentViewModel(
    private val agencyRepository: AgencyRepository = AppModule.agencyRepository,
    private val locationRepository: LocationRepository = AppModule.locationRepository
) : ViewModel() {

    private val _searchTextInput = MutableStateFlow<String>("")
    val searchTextInput: StateFlow<String> = _searchTextInput

    private val _uiClientListState = MutableStateFlow<UIState<ClientsResponse>>(UIState.Empty)
    val uiClientListState: StateFlow<UIState<ClientsResponse>> = _uiClientListState

    private val _uiAgencyState = MutableStateFlow<UIState<AgencyResponse>>(UIState.Loading)
    val uiAgencyState: StateFlow<UIState<AgencyResponse>> = _uiAgencyState

    private val _isOpenSheet = MutableStateFlow<Boolean>(false)
    val isOpenSheet: StateFlow<Boolean> = _isOpenSheet

    var agentNameInput by mutableStateOf<String>("")
    var phoneNumberInput by mutableStateOf<String>("")
    var provinceInput by mutableStateOf<String>("")
    var districtInput by mutableStateOf<String>("")
    var wardInput by mutableStateOf<String>("")
    var addressInput by mutableStateOf<String>("")

    private val _agencyState = MutableStateFlow<AgencyResponse?>(null)

    //distric
    private val _uiDistrictListState =
        MutableStateFlow<UIState<List<DistrictResponse>>>(UIState.Empty)
    val uiDistrictListState: StateFlow<UIState<List<DistrictResponse>>> = _uiDistrictListState

    private val _isExpandedDistrict = MutableStateFlow<Boolean>(false)
    val isExpandedDistrict: StateFlow<Boolean> = _isExpandedDistrict

    private val _selectedDistrict = MutableStateFlow<DistrictResponse?>(null)

    //ward
    private val _uiWardListState = MutableStateFlow<UIState<List<WardResponse>>>(UIState.Empty)
    val uiWardListState: StateFlow<UIState<List<WardResponse>>> = _uiWardListState

    private val _isExpandedWard = MutableStateFlow<Boolean>(false)
    val isExpandedWard: StateFlow<Boolean> = _isExpandedWard

    private var _selectedWard: WardResponse? = null

    //button confirm
    private val _uiConfirmButtonState = MutableStateFlow<UIButtonState>(UIButtonState.Enable)
    val uiConfirmButtonState: StateFlow<UIButtonState> = _uiConfirmButtonState

    var page = 1
    var pageSize = 10
    var agencyCode = ""

    fun onInitOpenSheet(agencyCodeValue: String) {
        viewModelScope.launch {
            agencyCode = agencyCodeValue
            handleSearchInput()
            fetchAgency()
        }
    }

    @OptIn(FlowPreview::class)
    private fun handleSearchInput() {
        viewModelScope.launch {
            _searchTextInput
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    fetchClients(query)
                }
        }
    }


    fun fetchAgency() {
        viewModelScope.launch {
            _uiAgencyState.emit(UIState.Loading)
            try {
                val response = agencyRepository.fetchAgency(
                    agencyId = agencyCode
                )
                if (response is ResultApi.Success) {
                    if (response.data != null) {
                        _uiAgencyState.emit(UIState.Success(response.data.data))
                        fetchClients(_searchTextInput.value)
                    } else {
                        _uiAgencyState.emit(UIState.Failure(Exception(AppLanguage.SOMETHING_WENT_WRONG)))
                    }
                }
            } catch (e: Exception) {
                _uiAgencyState.emit(UIState.Failure(e))
            }
        }
    }

    fun fetchClients(searchValue: String) {
        fetchData(
            uiState = _uiClientListState,
            fetchBlock = {
                agencyRepository.fetchClientsOfAgency(
                    agencyCode = agencyCode,
                    page = page,
                    pageSize = pageSize,
                    search = searchValue
                )
            }
        )
    }

    fun onRetrieveAgencyResult(newAgency: AgencyResponse) {
        if (newAgency != (_uiAgencyState.value as UIState.Success).data) {
            _uiAgencyState.value = UIState.Success(newAgency)
        }
    }

    fun onQueryChanged(newQuery: String) {
        _searchTextInput.value = newQuery
    }

    fun onRemoveInput() {
        _searchTextInput.value = ""
    }

    fun onSetValueOpenSheet(value: Boolean) {
        _isOpenSheet.value = value
    }

    fun onInitOpenSheet(value: AgencyResponse) {
        _agencyState.value = value
        _selectedDistrict.value = _agencyState.value?.location?.district
        _selectedWard = _agencyState.value?.location?.ward
        agentNameInput = _agencyState.value?.agentName ?: ""
        phoneNumberInput = _agencyState.value?.phoneNumber ?: ""
        districtInput = _selectedDistrict.value?.name ?: ""
        provinceInput = _agencyState.value?.location?.province?.name ?: ""
        wardInput = _selectedWard?.name ?: ""
        addressInput = _agencyState.value?.location?.address ?: ""
        fetchDistricts()
        fetchWards()
        setValueConfirmButtonState()
    }

    fun fetchDistricts() {
        fetchData(
            uiState = _uiDistrictListState,
            fetchBlock = { locationRepository.fetchDistricts(_agencyState.value!!.location.province.code) }
        )
    }

    fun fetchWards() {
        if (_selectedDistrict.value == null) return
        fetchData(
            uiState = _uiWardListState,
            fetchBlock = { locationRepository.fetchWard(_selectedDistrict.value!!.code) }
        )
    }

    //is Expanded
    fun onSetValueIsExpandedDistrict(value: Boolean) {
        _isExpandedDistrict.value = value
    }

    fun onSetValueIsExpandedWard(value: Boolean) {
        _isExpandedWard.value = value
    }

    //set selected
    fun setSelectedDistrict(value: String?) {
        viewModelScope.launch {
            _selectedWard = null
            wardInput = ""
            val district =
                (_uiDistrictListState.value as UIState.Success).data!!.firstOrNull { it.name == value }
            _selectedDistrict.emit(district)
            if (_selectedDistrict.value != null) {
                fetchWards()
            }
        }
    }

    fun setSelectedWard(value: String?) {
        val ward =
            (_uiWardListState.value as UIState.Success).data!!.firstOrNull { it.name == value }
        _selectedWard = ward
    }

    fun setValueConfirmButtonState() {
        if (Validation().validatePhoneNumber(phoneNumberInput) == null
            && Validation().validateEmpty(agentNameInput) == null
            && Validation().validateEmpty(provinceInput) == null
            && Validation().validateEmpty(districtInput) == null
            && Validation().validateEmpty(wardInput) == null
            && Validation().validateEmpty(addressInput) == null
        ) {
            _uiConfirmButtonState.value = UIButtonState.Enable
        } else {
            _uiConfirmButtonState.value = UIButtonState.Disable
        }
    }

    fun onConfirmCreateAgency(
        onDismissRequest: (Boolean) -> Unit,
        onAgencyResult: (AgencyResponse?) -> Unit,
    ) {
        viewModelScope.launch {
            _uiConfirmButtonState.emit(UIButtonState.Loading)
            val locationRequest = LocationRequest(
                provinceCode = _agencyState.value!!.location.province.code,
                districtCode = _selectedDistrict.value!!.code,
                wardCode = _selectedWard!!.code,
                address = addressInput
            )
            val agencyRequest = AgencyRequest(
                name = agentNameInput,
                phone = phoneNumberInput,
                location = locationRequest
            )
            try {
                val agencyCode = _agencyState.value!!.agentCode
                val response = agencyRepository.updateAgency(agencyCode, agencyRequest)
                if (response is ResultApi.Success) {
                    val locationResponse = LocationResponse(
                        district = _selectedDistrict.value,
                        province = _agencyState.value!!.location.province,
                        ward = _selectedWard,
                        address = addressInput
                    )
                    val newAgency = _agencyState.value!!.copy(
                        location = locationResponse,
                        agentName = agentNameInput,
                        phoneNumber = phoneNumberInput,
                    )

                    _agencyState.emit(newAgency)
                    _uiConfirmButtonState.emit(UIButtonState.Enable)
                    onAgencyResult(_agencyState.value)
                    onDismissRequest(false)
                }
            } catch (e: Exception) {
            }
        }
    }
}