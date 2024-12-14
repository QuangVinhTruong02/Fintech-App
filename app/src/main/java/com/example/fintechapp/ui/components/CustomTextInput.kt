package com.example.fintechapp.ui.components


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
    leadingIcon: Int? = null,
//    trailingIcon: Int? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    hintText: String,
    keyBoardType: KeyboardType = KeyboardType.Text,
    onValidate: ((String) -> String?)? = null,
    onValueChanged: (String) -> Unit,
    onTapTextField: (() -> Unit)? = null,
    maxChar: Int? = null,
    isPasswordVisible: Boolean = false,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier,
    enable: Boolean = true
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
        modifier = modifier
            .fillMaxWidth()
//            .heightIn(min = 48.dp)
            .clickable {
                onTapTextField?.invoke()
            },
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedBorderColor = AppColor.darkBlue,
            focusedBorderColor = AppColor.darkBlue,
        ),
        textStyle = AppTextStyle.latoMediumFontStyle,
        isError = errorText != null,
        enabled = enable,
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
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
        },
        trailingIcon = trailingIcon,
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
