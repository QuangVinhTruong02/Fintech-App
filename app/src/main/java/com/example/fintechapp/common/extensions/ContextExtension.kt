package com.example.fintechapp.common.extensions

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat


fun Context.goToApplicationSetting() {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}


fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}

fun Context.checkNotificationPermission(): Boolean {
    return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.POST_NOTIFICATIONS
    )
}

val Context.isDebugMode: Boolean
    get() = (this.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0)

fun Context.isFileLessThan5MB(uri: Uri): Boolean {
    val maxFileSize = 5 * 1024 * 1024L
    var isValid = false
    try {
        val fileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        fileDescriptor?.let {
            isValid = it.statSize < maxFileSize
        } ?: run {
            isValid = false
        }
        fileDescriptor?.close()
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return isValid
}

/*
 * DownloadManager request permission
 */
fun Context.checkWriteStoragePermission(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // DownloadManager delegates the downloading work to a separate system-supplied app
        return true
    } else PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
}

val Context.deviceAndroidId: String
    @SuppressLint("HardwareIds") get() {
        return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }


