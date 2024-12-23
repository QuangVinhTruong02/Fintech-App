package com.example.fintechapp.ui.screens.update_code_product

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintechapp.common.AppConst
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.Utils.ImageMultipart
import com.example.fintechapp.common.Utils.Validation
import com.example.fintechapp.data.remote.ResultApi
import com.example.fintechapp.data.request.CodeProductRequest
import com.example.fintechapp.data.request.ImagesRequest
import com.example.fintechapp.data.response.CodeProductResponse
import com.example.fintechapp.di.AppModule
import com.example.fintechapp.repository.CodeProductRepository
import com.example.fintechapp.repository.UploadRepository
import com.example.fintechapp.ui.base.DtgAppState
import com.example.fintechapp.ui.base.UIButtonState
import com.example.fintechapp.ui.base.UIState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class UpdateCodeProductViewModel(
    private val codeProductRepository: CodeProductRepository = AppModule.codeProductRepository,
    private val uploadRepository: UploadRepository = AppModule.uploadRepository
) : ViewModel() {
    var nameInput by mutableStateOf("")
    var descriptionInput by mutableStateOf("")

    private val _uiImageState = MutableStateFlow<UIState<String>>(UIState.Empty)
    val uiImageState: StateFlow<UIState<String>> = _uiImageState

    private val _uiButtonState = MutableStateFlow<UIButtonState>(UIButtonState.Disable)
    val uiButtonState: StateFlow<UIButtonState> = _uiButtonState

    private val _showNextProductDialog = MutableStateFlow<Boolean>(false)
    val showNextProductDialog: StateFlow<Boolean> = _showNextProductDialog

    private val _uiProductListState =
        MutableStateFlow<UIState<List<CodeProductResponse>>>(UIState.Empty)
    val uiProductListState: StateFlow<UIState<List<CodeProductResponse>>> = _uiProductListState

    private val _selectedNextProduct = MutableStateFlow<CodeProductResponse?>(null)
    val selectedNextProduct: StateFlow<CodeProductResponse?> = _selectedNextProduct

    private val _uiProductCurrentState =
        MutableStateFlow<UIState<CodeProductResponse>>(UIState.Empty)
    val uiProductCurrentState: StateFlow<UIState<CodeProductResponse>> = _uiProductCurrentState

    private var productCurrentId: Int? = null

    private val _searchTextInput = MutableStateFlow<String>("")
    val searchTextInput: StateFlow<String> = _searchTextInput

    init {
        fetchCodeProducts("")
        handleSearchInput()
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

    fun onSetValueShowNextProductDialog(value: Boolean) {
        _showNextProductDialog.value = value
    }

    fun onSetValueSelectedProduct(value: CodeProductResponse?) {
        if (value == _selectedNextProduct.value) {
            _selectedNextProduct.value = null
        } else {
            _selectedNextProduct.value = value
        }
    }

    fun onValidateButtonState() {
        if (Validation().validateEmpty(nameInput) == null) {
            _uiButtonState.value = UIButtonState.Enable
        } else {
            _uiButtonState.value = UIButtonState.Disable
        }
    }

    fun onSetSelectedImageState(value: UIState<String>) {
        _uiImageState.value = value
    }

    fun uploadImage(context: Context, uri: Uri?) {
        if (uri != null) {
            viewModelScope.launch {
                _uiImageState.emit(UIState.Loading)
                val multipartBody =
                    ImageMultipart().createMultipartFromUri(uri = uri, context = context)
                try {
                    val response = uploadRepository.uploadImage(multipartBody!!)
                    if (response is ResultApi.Success) {
                        if (response.data != null) {
                            _uiImageState.emit(UIState.Success(response.data))
                        } else {
                            _uiImageState.emit(UIState.Empty)
                        }
                    } else {
                        _uiImageState.emit(UIState.Failure(Exception((response as ResultApi.Error).exception)))
                    }
                } catch (e: Exception) {
                    _uiImageState.emit(UIState.Failure(e))
                }
            }
        }
    }

    fun fetchCodeProductCurrent(productId: Int) {
        viewModelScope.launch {
            productCurrentId = productId
            try {
                _uiProductCurrentState.emit(UIState.Loading)
                val response = codeProductRepository.fetchProduct(productId = productId)
                if (response is ResultApi.Success) {
                    if (response.data != null) {
                        _uiProductCurrentState.emit(UIState.Success(response.data))
                        val codeProduct = response.data
                        nameInput = codeProduct.nameProduct
                        descriptionInput = codeProduct.description?: ""
                        _selectedNextProduct.emit(codeProduct.nextProduct)
                        if (codeProduct.images.isNotEmpty()) {
                            _uiImageState.emit(UIState.Success(codeProduct.images[0].urlImage))
                        }
                        onValidateButtonState()
                    } else {
                        _uiProductCurrentState.emit(UIState.Failure(Exception(AppLanguage.SOMETHING_WENT_WRONG)))
                    }
                } else {
                    _uiProductCurrentState.emit(UIState.Failure(Exception((response as ResultApi.Error).exception)))
                }
            } catch (e: Exception) {
                _uiProductCurrentState.emit(UIState.Failure(e))

            }
        }
    }

    fun fetchCodeProducts(query: String) {
        viewModelScope.launch {
            _uiProductListState.value = UIState.Loading
            try {
                val response = codeProductRepository.fetchProducts(
                    page = 1,
                    pageSize = 30,
                    search = query
                )
                if (response is ResultApi.Success) {
                    _uiProductListState.value = UIState.Success(
                        response.data?.codeProductList ?: emptyList()
                    )
                } else {
                    _uiProductListState.emit(UIState.Failure(Exception(AppLanguage.SOMETHING_WENT_WRONG)))
                }
            } catch (e: Exception) {
                _uiProductListState.emit(UIState.Failure(e))
            }
        }
    }

    fun onUpdateProduct(
        appState: DtgAppState,
        onNavigateBack: () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                _uiButtonState.emit(UIButtonState.Loading)
                val images = mutableListOf<ImagesRequest>()
                if (_uiImageState.value is UIState.Success) {
                    images.add(ImagesRequest(url = (_uiImageState.value as UIState.Success).data!!))
                }
                val codeProductRequest = CodeProductRequest(
                    name = nameInput,
                    description = descriptionInput,
                    nextProductId = _selectedNextProduct.value?.id,
                    images = images
                )
                if (productCurrentId == null) {
                }
                val response =
                    codeProductRepository.updateProduct(
                        productId = productCurrentId!!,
                        codeProductRequest = codeProductRequest
                    )
                if (response is ResultApi.Success) {
                    _uiButtonState.emit(UIButtonState.Enable)
                    appState.appNavigation.navController.previousBackStackEntry?.savedStateHandle?.set(
                        AppConst.PRODUCT_RETURN_KEY,
                        true
                    )
                    onNavigateBack()
                } else {
                }
            } catch (e: Exception) {

            }
        }
    }
}