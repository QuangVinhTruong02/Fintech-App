package com.example.fintechapp.ui.components

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.util.Log
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Date

@Composable
fun CustomDatePicker(
    onSelectedDateValue: (String) -> Unit,
    initialDate: String? = null
) {

    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val mDate = remember { mutableStateOf("") }

    //Initial a calendar
    val calendar = Calendar.getInstance()
    initialDate?.let {
        val dateParts = initialDate.split("/")
        val initialYear = dateParts[2].toInt()
        val initialMonth = dateParts[1].toInt()
        val initialDayOfMonth = dateParts[0].toInt()
        calendar.set(
            initialYear, initialMonth, initialDayOfMonth
        )
    }
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            Log.d("MYAP", "456")
            mDate.value = "$dayOfMonth/$month/$year"
            onSelectedDateValue(mDate.value)
        },
        year, month, day,
    )
    datePickerDialog.setOnCancelListener {
        onSelectedDateValue(initialDate ?: "")
    }
    datePickerDialog.show()
}