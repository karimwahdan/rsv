package com.karimwahdan.rsv

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun QuantityValue(color: Color, value: MutableIntState) {
    val modifier=Modifier.padding(horizontal = 25.dp)
    Text(modifier = modifier,text = value.intValue.toString(),color = color)
}