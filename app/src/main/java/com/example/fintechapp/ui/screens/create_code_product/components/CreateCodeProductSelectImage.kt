package com.example.fintechapp.ui.screens.create_code_product.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.fintechapp.common.AppColor
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppImage
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.example.fintechapp.ui.base.UIState
import com.example.fintechapp.ui.screens.create_code_product.CreateCodeProductViewModel

@Composable

fun CreateCodeProductSelectImage(viewModel: CreateCodeProductViewModel) {

    val uiSelectedImageState: UIState<String> by viewModel.uiImageState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val singleImagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            viewModel.uploadImage(context = context, uri = uri)
        }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .border(shape = RoundedCornerShape(8.dp), width = 0.5.dp, color = AppColor.lightGrey)
            .clickable {
                singleImagePickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            },
        contentAlignment = Alignment.Center
    ) {

        when (uiSelectedImageState) {
            is UIState.Empty -> {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(AppImage.imgUploadImage),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(
                        AppLanguage.SELECT_IMAGE,
                        style = AppTextStyle.latoRegularFontStyle.copy(color = AppColor.lightGrey)
                    )
                }
            }

            is UIState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.align(Alignment.TopEnd)) {
                        CircularProgressIndicator()
                    }
                }
            }

            is UIState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        modifier = Modifier.align(Alignment.Center),
                        model = (uiSelectedImageState as UIState.Success).data,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    IconButton(
                        modifier = Modifier.align(Alignment.TopEnd),
                        onClick = {
                            viewModel.onSetSelectedImageState(UIState.Empty)
                        }
                    ) {
                        Icon(
                            painter = painterResource(AppIcon.icClose),
                            contentDescription = null
                        )
                    }
                }
            }

            is UIState.Failure -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        "${(uiSelectedImageState as UIState.Failure).throwable}",
                        style = AppTextStyle.latoRegularFontStyle.copy(color = AppColor.red)
                    )
                }
            }
        }

//        if (selectedImage != null) {
//            Box(modifier = Modifier.fillMaxSize()) {
//                AsyncImage(
//                    modifier = Modifier.align(Alignment.Center),
//                    model = selectedImage,
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop
//                )
//                IconButton(
//                    modifier = Modifier.align(Alignment.TopEnd),
//                    onClick = { viewModel.onSetValueSelectedImage(null) }
//                ) {
//                    Icon(
//                        painter = painterResource(AppIcon.icClose),
//                        contentDescription = null
//                    )
//                }
//            }
//        } else {
//
//        }
    }
}