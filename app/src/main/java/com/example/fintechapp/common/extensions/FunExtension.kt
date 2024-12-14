package com.example.fintechapp.common.extensions

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<Boolean>.setExpandedTextFieldDropDown(
    isExpanded: Boolean,
    selectedItem: T?,
    inputValue: String,
    itemNameSelector: (T) -> String?,
    onItemCleared: () -> Unit
){
    if (!isExpanded && selectedItem?.let { itemNameSelector(it) } != inputValue) {
        onItemCleared()
    }
    value = isExpanded
}
