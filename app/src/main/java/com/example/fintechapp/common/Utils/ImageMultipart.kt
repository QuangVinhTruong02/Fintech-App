package com.example.fintechapp.common.Utils

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ImageMultipart{
    fun createMultipartFromUri(context: Context, uri: Uri, partName: String = "file"): MultipartBody.Part? {
        val contentResolver = context.contentResolver
        val mimeType = contentResolver.getType(uri) ?: "application/octet-stream"
        val inputStream = contentResolver.openInputStream(uri) ?: return null

        val file = File(context.cacheDir, "upload_file").apply {
            outputStream().use { output ->
                inputStream.copyTo(output)
            }
        }

        val requestBody = file.asRequestBody(mimeType.toMediaTypeOrNull())
        return MultipartBody.Part.createFormData(partName, file.name, requestBody)
    }

}