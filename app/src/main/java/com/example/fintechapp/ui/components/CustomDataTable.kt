package com.example.fintechapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.fintechapp.common.AppIcon
import com.example.fintechapp.common.AppLanguage
import com.example.fintechapp.common.AppTextStyle
import com.seanproctor.datatable.BasicDataTable
import com.seanproctor.datatable.DataColumn
import com.seanproctor.datatable.DataTableScope
import com.seanproctor.datatable.TableColumnWidth
import com.seanproctor.datatable.rememberDataTableState

@Composable
fun CustomDataTable(
    headerList: List<DataColumn>,
    content: DataTableScope.() -> Unit,
) {
//    val checkboxesState = remember { mutableStateListOf(*Array(20) { false }) }

    BasicDataTable(
        columns = headerList,
        separator = {
            HorizontalDivider()
        },
        content = content,
        modifier = Modifier.fillMaxSize(),
        headerBackgroundColor = Color.Gray.copy(alpha = 0.2f),
        contentPadding = PaddingValues(0.dp),
        headerHeight = 60.dp,
        rowHeight = 60.dp
    )
}