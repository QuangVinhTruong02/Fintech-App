package com.example.fintechapp.ui.screens.create_agent

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.request.AgencyRequest
import com.example.fintechapp.data.request.LocationRequest
import com.example.fintechapp.data.response.AgencyResponse
import com.example.fintechapp.data.response.DistrictResponse
import com.example.fintechapp.data.response.LocationResponse
import com.example.fintechapp.data.response.ProvinceResponse
import com.example.fintechapp.data.response.WardResponse
import com.example.fintechapp.di.AppModule
import com.example.fintechapp.repository.AgencyRepository
import com.example.fintechapp.repository.LocationRepository
import com.example.fintechapp.ui.base.UIButtonState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class CreateAgentViewModel(
    private val locationRepository: LocationRepository = AppModule.locationRepository,
    private val agencyRepository: AgencyRepository = AppModule.agencyRepository
) : ViewModel() {
    var agentNameInput by mutableStateOf<String>("")
    var phoneNumberInput by mutableStateOf<String>("")
    var provinceInput by mutableStateOf<String>("")
    var districtInput by mutableStateOf<String>("")
    var wardInput by mutableStateOf<String>("")
    var addressInput by mutableStateOf<String>("")

    private val _agencyId = MutableStateFlow<String>("")
    val agencyId: StateFlow<String> = _agencyId

    private val _provincesList = MutableStateFlow<List<ProvinceResponse>>(emptyList())
    val provinceList: StateFlow<List<ProvinceResponse>> = _provincesList

    private val _districtList = MutableStateFlow<List<DistrictResponse>>(emptyList())
    val districtList: StateFlow<List<DistrictResponse>> = _districtList

    private val _wardList = MutableStateFlow<List<WardResponse>>(emptyList())
    val wardList: StateFlow<List<WardResponse>> = _wardList

    private val _isProvinceLoading = MutableStateFlow<Boolean>(false)
    val isProvinceLoading: StateFlow<Boolean> = _isProvinceLoading

    private val _isDistrictLoading = MutableStateFlow<Boolean>(false)
    val isDistrictLoading: StateFlow<Boolean> = _isDistrictLoading

    private val _isWardLoading = MutableStateFlow<Boolean>(false)
    val isWardLoading: StateFlow<Boolean> = _isWardLoading

    private val _selectedProvince = MutableStateFlow<ProvinceResponse?>(null)

    private val _selectedDistrict = MutableStateFlow<DistrictResponse?>(null)

    private val _selectedWard = MutableStateFlow<WardResponse?>(null)

    private val _isExpandedProvince = MutableStateFlow<Boolean>(false)
    val isExpandedProvince: StateFlow<Boolean> = _isExpandedProvince

    private val _isExpandedDistrict = MutableStateFlow<Boolean>(false)
    val isExpandedDistrict: StateFlow<Boolean> = _isExpandedDistrict

    private val _isExpandedWard = MutableStateFlow<Boolean>(false)
    val isExpandedWard: StateFlow<Boolean> = _isExpandedWard

    private val _uiButtonConfirmState = MutableStateFlow<UIButtonState>(UIButtonState.Disable)
    val uiButtonConfirmState: StateFlow<UIButtonState> = _uiButtonConfirmState


    fun setAgencyId(value: String) {
        _agencyId.value = value
        if (_agencyId.value.isNotEmpty()) {
            fetchAgency()
        }
    }

    fun setIsExpandedProvince(value: Boolean) {
        if (!value && _selectedProvince.value?.name != provinceInput) {
            setSelectedProvince(null)
            provinceInput = ""
            _districtList.value = emptyList()
        }
        _isExpandedProvince.value = value
    }

    fun setIsExpandedDistrict(value: Boolean) {
        if (!value && _selectedDistrict.value?.name != districtInput) {
            setSelectedDistrict(null)
            districtInput = ""
            _wardList.value = emptyList()
        }
        _isExpandedDistrict.value = value
    }

    fun setIsExpandedWard(value: Boolean) {
        if (!value && _selectedWard.value?.name != wardInput) {
            setSelectedWard(null)
            wardInput = ""
        }
        _isExpandedWard.value = value
    }

    fun fetchAgency() {
        viewModelScope.launch {
            val response = agencyRepository.fetchAgency(_agencyId.value)
            if (response is ResultApi.Success) {
                val agency: AgencyResponse = response.data!!.data
                agentNameInput = agency.agentName
                phoneNumberInput = agency.phoneNumber
                _selectedProvince.value = agency.location.province
                provinceInput = agency.location.province.name
                _selectedDistrict.value = agency.location.district
                districtInput = agency.location.district.name
                _selectedWard.value = agency.location.ward
                wardInput = agency.location.ward.name
                addressInput = agency.location.address
                fetchProvinces()
                fetchDistricts()
                fetchWards()
                setValueConfirmButtonState()
            }
        }
    }

    fun fetchProvinces() {
        viewModelScope.launch {
            _isProvinceLoading.emit(true)
            val response = locationRepository.fetchProvinces()
            if (response is ResultApi.Success) {
                _provincesList.value = response.data?.data ?: emptyList()
            }
            _isProvinceLoading.emit(false)
        }
    }

    fun fetchDistricts() {
        if (_selectedProvince.value == null) return
        viewModelScope.launch {
            _isDistrictLoading.emit(true)
            val response = locationRepository.fetchDistricts(_selectedProvince.value!!.code)
            if (response is ResultApi.Success) {
                _districtList.value = response.data?.data ?: emptyList()
            }
            _isDistrictLoading.emit(false)
        }
    }

    fun fetchWards() {
        if (_selectedDistrict.value == null) return
        viewModelScope.launch {
            _isWardLoading.emit(true)
            val response = locationRepository.fetchWard(_selectedProvince.value!!.code)
            if (response is ResultApi.Success) {
                _wardList.value = response.data?.data ?: emptyList()
            }
            _isWardLoading.emit(false)
        }
    }

    fun setSelectedProvince(value: String?) {
        viewModelScope.launch {
            val province = _provincesList.value.firstOrNull { it.name == value }
            _selectedProvince.emit(province)
            if (_selectedProvince.value != null) {
                fetchDistricts()
            }
        }
    }

    fun setSelectedDistrict(value: String?) {
        viewModelScope.launch {
            val district = _districtList.value.firstOrNull { it.name == value }
            _selectedDistrict.emit(district)
            if (_selectedDistrict.value != null) {
                fetchWards()
            }
        }
    }

    fun setSelectedWard(value: String?) {
        viewModelScope.launch {
            val ward = _wardList.value.firstOrNull { it.name == value }
            _selectedWard.emit(ward)

        }
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
        onPreviousBackStackAgency: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _uiButtonConfirmState.emit(UIButtonState.Loading)
            val locationRequest: LocationRequest = LocationRequest(
                provinceCode = _selectedProvince.value!!.code,
                districtCode = _selectedDistrict.value!!.code,
                wardCode = _selectedWard.value!!.code,
                address = addressInput
            )
            val agencyRequest: AgencyRequest = AgencyRequest(
                name = agentNameInput,
                phone = phoneNumberInput,
                location = locationRequest
            )
            if (_agencyId.value.isEmpty()) {
                val response = agencyRepository.createAgency(agencyRequest)
                if (response is ResultApi.Success) {
                    _uiButtonConfirmState.emit(UIButtonState.Enable)
                    onPreviousBackStackAgency(true)
                }
            } else {
                val response = agencyRepository.updateAgency(_agencyId.value, agencyRequest)
                if (response is ResultApi.Success) {
                    _uiButtonConfirmState.emit(UIButtonState.Enable)
                    onPreviousBackStackAgency(true)
                }
            }
        }
    }
}