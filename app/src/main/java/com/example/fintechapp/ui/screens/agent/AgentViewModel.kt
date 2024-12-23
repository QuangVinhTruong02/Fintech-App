package com.example.fintechapp.ui.screens.agent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.common.extensions.fetchData
import com.example.fintechapp.common.extensions.setExpandedTextFieldDropDown
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.request.AgencyRequest
import com.example.fintechapp.data.request.LocationRequest
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.data.response.DistrictResponse
import com.example.fintechapp.data.response.ProvinceResponse
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

@OptIn(FlowPreview::class)
class AgentViewModel(
    private val agencyRepository: AgencyRepository = AppModule.agencyRepository,
    private val locationRepository: LocationRepository = AppModule.locationRepository,
) : ViewModel() {
    private var page = 1
    private var pageSize = 10
    private var count = 10
    private val _totalCount = MutableStateFlow<Int>(0);
    val totalCount : StateFlow<Int> = _totalCount

    var agentNameInput by mutableStateOf("")
    var phoneNumberInput by mutableStateOf("")
    var provinceInput by mutableStateOf("")
    var districtInput by mutableStateOf("")
    var wardInput by mutableStateOf("")
    var addressInput by mutableStateOf("")

    private val _agency = MutableStateFlow<AgencyResponse?>(null)
    val agency: StateFlow<AgencyResponse?> = _agency

    private var _selectedProvince: ProvinceResponse? = null
    private var _selectedDistrict: DistrictResponse? = null
    private var _selectedWard: WardResponse? = null

    private val _uiProvinceListState =
        MutableStateFlow<UIState<List<ProvinceResponse>>>(UIState.Empty)
    val uiProvinceListState: StateFlow<UIState<List<ProvinceResponse>>> = _uiProvinceListState

    private val _uiDistrictListState =
        MutableStateFlow<UIState<List<DistrictResponse>>>(UIState.Empty)
    val uiDistrictListState: StateFlow<UIState<List<DistrictResponse>>> = _uiDistrictListState

    private val _uiWardListState =
        MutableStateFlow<UIState<List<WardResponse>>>(UIState.Empty)
    val uiWardListState: StateFlow<UIState<List<WardResponse>>> = _uiWardListState

    private val _isExpandedProvince = MutableStateFlow(false)
    val isExpandedProvince: StateFlow<Boolean> = _isExpandedProvince

    private val _isExpandedDistrict = MutableStateFlow(false)
    val isExpandedDistrict: StateFlow<Boolean> = _isExpandedDistrict

    private val _isExpandedWard = MutableStateFlow(false)
    val isExpandedWard: StateFlow<Boolean> = _isExpandedWard

    private val _uiButtonConfirmState = MutableStateFlow<UIButtonState>(UIButtonState.Disable)
    val uiButtonConfirmState: StateFlow<UIButtonState> = _uiButtonConfirmState

    private val _uiAgencyListState = MutableStateFlow<UIState<List<AgencyResponse>>>(UIState.Empty)
    val uiAgencyListState: StateFlow<UIState<List<AgencyResponse>>> = _uiAgencyListState

    private val _searchTextInput = MutableStateFlow<String>("")
    val searchTextInput: StateFlow<String> = _searchTextInput

    private val _preHasDataToFetch = MutableStateFlow<Boolean>(false)
    val preHasDataToFetch: StateFlow<Boolean> = _preHasDataToFetch

    private val _nextHasDataToFetch = MutableStateFlow<Boolean>(true)
    val nextHasDataToFetch: StateFlow<Boolean> = _nextHasDataToFetch

    private val _showDialog = MutableStateFlow<Boolean>(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private val _selectedAgency = MutableStateFlow<AgencyResponse?>(null)
    val selectedAgency: StateFlow<AgencyResponse?> = _selectedAgency

    private val _isOpenSheet = MutableStateFlow<Boolean>(false)
    val isOpenSheet: StateFlow<Boolean> = _isOpenSheet

    fun onInit() {
        fetchAgencies(_searchTextInput.value)
        handleSearchInput()
        fetchProvinces()
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

    fun onInitOpenSheet(agencyValue: AgencyResponse?) {
        if (agencyValue != null) {
            _agency.value = agencyValue
            _selectedProvince = _agency.value?.location?.province
            _selectedDistrict = _agency.value?.location?.district
            _selectedWard = _agency.value?.location?.ward
            agentNameInput = _agency.value?.agentName ?: ""
            phoneNumberInput = _agency.value?.phoneNumber ?: ""
            provinceInput = _agency.value?.location?.province?.name ?: ""
            districtInput = _agency.value?.location?.district?.name ?: ""
            wardInput = _agency.value?.location?.ward?.name ?: ""
            addressInput = _agency.value?.location?.address ?: ""
            fetchProvinces()
            fetchDistricts()
            fetchWards()
            setValueConfirmButtonState()
        }else{
            onClearInput()
        }
    }

    fun onTurnOffShowDialog(){
        _uiAgencyListState.value = UIState.Empty
    }

    fun fetchAgencies(query: String) {
        viewModelScope.launch {
            _uiAgencyListState.emit(UIState.Loading)
            try {
                val response =
                    agencyRepository.fetchAgencies(page, pageSize, query)
                if (response is ResultApi.Success) {
                    _uiAgencyListState.emit(UIState.Success(response.data!!.agentList))
                    _totalCount.emit(response.data.count)
                    setValueForHasData()
                }
            } catch (e: Exception) {
                _uiAgencyListState.emit(UIState.Failure(e))
            }
        }
    }

    fun onSetValueOpenSheet(value: Boolean) {
        _isOpenSheet.value = value
    }

    private fun setValueForHasData() {
        _nextHasDataToFetch.value = count < totalCount.value
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

    fun setSelectedAgency(agencyResponse: AgencyResponse?) {
        _selectedAgency.value = agencyResponse
    }

    fun onPageIncrement() {
        if (count < totalCount.value) {
            page += 1
            count += 10
            setValueForHasData()
            fetchAgencies(_searchTextInput.value)
        }
    }


    fun onPageDecrement() {
        if (page > 1) {
            page -= 1
            count -= 10
            setValueForHasData()
            fetchAgencies(_searchTextInput.value)
        }
    }

    fun onRemoveInput() {
        _searchTextInput.value = ""
    }

    fun setIsExpandedProvince(value: Boolean) {
        _isExpandedProvince.setExpandedTextFieldDropDown(
            isExpanded = value,
            itemNameSelector = { it.name },
            selectedItem = _selectedProvince,
            inputValue = provinceInput,
            onItemCleared = {
                provinceInput = ""
                _selectedProvince = null
            }
        )
    }

    fun setIsExpandedDistrict(value: Boolean) {
        _isExpandedDistrict.setExpandedTextFieldDropDown(
            isExpanded = value,
            inputValue = districtInput,
            onItemCleared = {
                districtInput = ""
                _selectedDistrict = null
            },
            selectedItem = _selectedDistrict,
            itemNameSelector = { it.name }
        )
    }

    fun setIsExpandedWard(value: Boolean) {
        _isExpandedWard.setExpandedTextFieldDropDown(
            isExpanded = value,
            inputValue = wardInput,
            onItemCleared = {
                wardInput = ""
                _selectedWard = null
            },
            selectedItem = _selectedWard,
            itemNameSelector = { it.name }
        )
    }

    fun fetchProvinces() {
        fetchData(
            uiState = _uiProvinceListState,
            fetchBlock = { locationRepository.fetchProvinces() }
        )
    }

    fun fetchDistricts() {
        if (_selectedProvince == null) return
        fetchData(
            uiState = _uiDistrictListState,
            fetchBlock = { locationRepository.fetchDistricts(_selectedProvince!!.code) }
        )
    }

    fun fetchWards() {
        if (_selectedDistrict == null) return
        fetchData(
            uiState = _uiWardListState,
            fetchBlock = { locationRepository.fetchWard(_selectedDistrict!!.code) }
        )
    }

    fun setSelectedProvince(value: String?) {
        val province =
            (_uiProvinceListState.value as UIState.Success).data?.firstOrNull { it.name == value }
        _selectedProvince = province
        _selectedDistrict = null
        _selectedWard = null
        districtInput = ""
        wardInput = ""
        if (_selectedProvince != null) {
            fetchDistricts()
        }
    }

    fun setSelectedDistrict(value: String?) {
        val district =
            (_uiDistrictListState.value as UIState.Success).data?.firstOrNull { it.name == value }
        _selectedDistrict = district
        _selectedWard = null
        wardInput = ""
        if (_selectedDistrict != null) {
            fetchWards()
        }
    }

    fun setSelectedWard(value: String?) {
        val ward =
            (_uiWardListState.value as UIState.Success).data?.firstOrNull { it.name == value }
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
            _uiButtonConfirmState.value = UIButtonState.Enable
        } else {
            _uiButtonConfirmState.value = UIButtonState.Disable
        }
    }

    fun onConfirmCreateAgency(
        onHasData: (Boolean) -> Unit,
        onDismissRequest: (Boolean) -> Unit,
    ) {
        viewModelScope.launch {
            _uiButtonConfirmState.emit(UIButtonState.Loading)
            val locationRequest = LocationRequest(
                provinceCode = _selectedProvince!!.code,
                districtCode = _selectedDistrict!!.code,
                wardCode = _selectedWard!!.code,
                address = addressInput
            )
            val agencyRequest = AgencyRequest(
                name = agentNameInput,
                phone = phoneNumberInput,
                location = locationRequest
            )
            if (_agency.value == null) {
                val response = agencyRepository.createAgency(agencyRequest)
                if (response is ResultApi.Success) {
                    _uiButtonConfirmState.emit(UIButtonState.Enable)
                }
            } else {
                val response =
                    agencyRepository.updateAgency(_agency.value!!.agentCode, agencyRequest)
                if (response is ResultApi.Success) {
                    _uiButtonConfirmState.emit(UIButtonState.Enable)
                }
            }
            onHasData(true)
            onDismissRequest(false)
            onClearInput()
        }
    }

    fun onClearInput() {
        _selectedProvince = null
        _selectedWard = null
        _selectedDistrict = null
        provinceInput = ""
        wardInput = ""
        agentNameInput = ""
        phoneNumberInput = ""
        wardInput = ""
        districtInput = ""
        addressInput = ""
        _uiProvinceListState.value = UIState.Empty
        _uiDistrictListState.value = UIState.Empty
        _uiWardListState.value = UIState.Empty
    }
}