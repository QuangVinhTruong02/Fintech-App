package com.example.fintechapp.ui.screens.code_product

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.extensions.fetchData
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.response.CodeProductResponse
import com.example.fintechapp.data.response.CodeProductsResponse
import com.example.fintechapp.di.AppModule
import com.example.fintechapp.repository.CodeProductRepository
import com.example.fintechapp.ui.base.UIState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class CodeProductViewModel(
    private val codeProductRepository: CodeProductRepository = AppModule.codeProductRepository
) : ViewModel() {
    private val _uiProductListState =
        MutableStateFlow<UIState<List<CodeProductResponse>>>(UIState.Empty)
    val uiProductListState: StateFlow<UIState<List<CodeProductResponse>>> = _uiProductListState

    private val _uiProductState = MutableStateFlow<UIState<CodeProductResponse>>(UIState.Empty)
    val uiProductState: StateFlow<UIState<CodeProductResponse>> = _uiProductState

    private val _showDetailDialog = MutableStateFlow<Boolean>(false)
    val showDetailDialog: StateFlow<Boolean> = _showDetailDialog

    private val _preHasDataToFetch = MutableStateFlow<Boolean>(false)
    val preHasDataToFetch: StateFlow<Boolean> = _preHasDataToFetch

    private val _nextHasDataToFetch = MutableStateFlow<Boolean>(true)
    val nextHasDataToFetch: StateFlow<Boolean> = _nextHasDataToFetch

    private val _totalCount = MutableStateFlow<Int>(0);
    val totalCount: StateFlow<Int> = _totalCount

    private val _searchTextInput = MutableStateFlow<String>("")
    val searchTextInput: StateFlow<String> = _searchTextInput

    private val _showRemoveDialog = MutableStateFlow<Boolean>(false)
    val showRemoveDialog : StateFlow<Boolean> = _showRemoveDialog

    private var page: Int = 1
    private var pageSize: Int = 10
    private var count = 10

    private var _selectedProductId: Int? = null

    fun onInit(){
        handleSearchInput()
        fetchCodeProducts(_searchTextInput.value)
    }

    @OptIn(FlowPreview::class)
    private fun handleSearchInput() {
        viewModelScope.launch {
            _searchTextInput
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    fetchCodeProducts(query)
                }
        }
    }

    fun onQueryChanged(newQuery: String) {
        _searchTextInput.value = newQuery
    }

    fun setSelectedProductId(value: Int?) {
        _selectedProductId = value
    }

    val selectedProductId: Int? get() = _selectedProductId

    fun onSetValueRemoveDialog(value: Boolean){
        _showRemoveDialog.value = value
    }

    fun fetchCodeProducts(query: String) {
        viewModelScope.launch {
            _uiProductListState.emit(UIState.Loading)
            try {
                val response = codeProductRepository.fetchProducts(
                    page = page,
                    pageSize = pageSize,
                    search = query
                )
                if (response is ResultApi.Success) {
                    if (response.data != null) {
                        _uiProductListState.emit(UIState.Success(response.data.codeProductList))
                        _totalCount.emit(response.data.count)
                        setValueForHasData()
                    } else {
                        _uiProductListState.emit(
                            UIState.Success(emptyList())
                        )
                    }
                } else {
                    _uiProductListState.emit(UIState.Failure(Exception(AppLanguage.SOMETHING_WENT_WRONG)))
                }
            } catch (e: Exception) {
                _uiProductListState.emit(UIState.Failure(e))
            }
        }
    }

    fun fetchCodeProduct(productId: Int) {
        viewModelScope.launch {
            fetchData(
                uiState = _uiProductState,
                fetchBlock = { codeProductRepository.fetchProduct(productId = productId) }
            )
        }
    }

    fun onDeleteProductById(){
        viewModelScope.launch {
            if(_selectedProductId != null){
                try{
                   val response = codeProductRepository.deleteProductById(_selectedProductId!!)
                   if(response is ResultApi.Success){
                       val codeProducts = (_uiProductListState.value as UIState.Success<List<CodeProductResponse>>).data?.toMutableList()
                       codeProducts?.removeIf{it.id == _selectedProductId}
                       _selectedProductId = null
                       _uiProductListState.value = UIState.Success(codeProducts)
                   }else{

                   }
                }catch (e: Exception){
                }
            }
        }
    }

    fun onSetValueShowDetailDialog(value: Boolean) {
        _showDetailDialog.value = value
    }

    fun onPageIncrement() {
        if (count < totalCount.value) {
            page += 1
            count += 10
            setValueForHasData()
            fetchCodeProducts(_searchTextInput.value)
        }
    }


    fun onPageDecrement() {
        if (page > 1) {
            page -= 1
            count -= 10
            setValueForHasData()
            fetchCodeProducts(_searchTextInput.value)
        }
    }

    private fun setValueForHasData() {
        _nextHasDataToFetch.value = count < totalCount.value
        _preHasDataToFetch.value = page != 1
    }
}