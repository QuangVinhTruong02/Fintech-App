package com.example.fintechapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage

@SuppressLint("RememberReturnType")
@Composable
fun CustomTextFieldDropDown(
    isExpanded: Boolean,
    valueText: String,
    hintText: String,
    onValueChanged: (String) -> Unit,
    onSelectedValue: (String) -> Unit,
    onValidation: ((String) -> String?)? = null,
    onTapTextField: () -> Unit,
    setIsExpanded: (Boolean) -> Unit,
    optionList: List<String>,
    isLoading: Boolean = false,
    enable: Boolean = true
) {
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {}
            )
    ) {
        CustomTextInput(
            valueText = valueText,
            hintText = hintText,
            enable = enable,
            onValueChanged = {
                onValueChanged(it)
            },
            onTapTextField = {
                onTapTextField()
                setIsExpanded(true)
            },
            onValidate = onValidation,
            modifier = Modifier.onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            },
            trailingIcon = {
                Row {
                    if (isLoading) {
                        CircularProgressIndicator()
                    }
                    IconButton(
                        onClick = {
                            setIsExpanded(!isExpanded)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = AppIcon.icDropDown),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            },
        )
        AnimatedVisibility(visible = isExpanded) {
            Card(
                modifier = Modifier
                    .width(textFieldSize.width.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                if (optionList.isEmpty()) {
                    CategoryItems(title = AppLanguage.NOTHING) {}
                } else {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 150.dp),
                    ) {
                        if (valueText.isNotEmpty()) {
                            items(
                                optionList.filter {
                                    it.lowercase()
                                        .contains(valueText.lowercase()) || it.lowercase()
                                        .contains("others")
                                }
                            ) {
                                CategoryItems(title = it) { title ->
                                    onValueChanged(title)
                                    onSelectedValue(title)
                                    setIsExpanded(false)
                                }
                            }
                        } else {
                            items(
                                optionList
                            ) {
                                CategoryItems(title = it) { title ->
                                    onValueChanged(title)
                                    onSelectedValue(title)
                                    setIsExpanded(false)
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun CategoryItems(
    title: String,
    onSelect: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }

}