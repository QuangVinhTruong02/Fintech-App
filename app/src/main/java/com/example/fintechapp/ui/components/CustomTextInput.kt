package com.example.fintechapp.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppTextStyle

@Composable
fun CustomTextInput(
    valueText: String,
    leadingIcon: Int,
    trailingIcon: Int? = null,
    hintText: String,
    keyBoardType: KeyboardType = KeyboardType.Text,
    onValidate: ((String) -> String?)? = null,
    onValueChanged: (String) -> Unit,
    onTapTextField: (() -> Unit)? = null,
    onPressedTrailingIcon: (() -> Unit)? = null,
    maxChar: Int? = null,
    isPasswordVisible: Boolean = false,
    readOnly: Boolean = false
) {
    var errorText by remember { mutableStateOf<String?>(null) }
    OutlinedTextField(
        value = valueText,
        onValueChange = { newValue ->
            if (maxChar == null || newValue.length <= maxChar) {
                onValueChanged(newValue)
            }
            errorText = onValidate?.invoke(newValue)
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp)
            .clickable {
                onTapTextField?.invoke()
            },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedBorderColor = AppColor.darkBlue,
            focusedBorderColor = AppColor.darkBlue,
        ),
        textStyle = AppTextStyle.latoMediumFontStyle,
        isError = errorText != null,
        readOnly = readOnly,
        visualTransformation = if (isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
            LaunchedEffect(interactionSource) {
                interactionSource.interactions.collect {
                    if (it is PressInteraction.Release) {
                        onTapTextField?.invoke()
                    }
                }
            }
        },
        supportingText = {
            errorText?.let { errorText ->
                Text(
                    errorText,
                    style = AppTextStyle.latoRegularFontStyle.copy(
                        fontSize = 12.sp,
                        color = AppColor.red
                    )
                )
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType),
        singleLine = true,
        leadingIcon = {
            Icon(painter = painterResource(id = leadingIcon), contentDescription = null)
        },
        trailingIcon = trailingIcon?.let {
            {
                IconButton(
                    onClick = { onPressedTrailingIcon?.invoke() }
                ) {
                    Icon(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = null
                    )
                }
            }
        },
        placeholder = {
            Text(
                hintText,
                style = AppTextStyle.interRegularFontStyle.copy(
                    fontSize = 12.sp,
                    color = AppColor.grey
                )
            )
        },
    )
}
