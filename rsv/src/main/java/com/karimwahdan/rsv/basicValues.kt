package com.karimwahdan.rsv

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


const val comma=" , "
const val doubleDots=":"
const val space=" "
const val Normal_Horizontal_List_Style="style_1"
const val Four_Cards_List_Style="style_2"
const val One_Big_One_Small_List_Style="style_3"

val roundedCornerShape20dpTop = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)

val registerScreenModifier = Modifier
    .fillMaxSize().border(width = 1.dp, color = Color.Gray,shape = roundedCornerShape20dpTop)
    .clip(roundedCornerShape20dpTop).padding(10.dp)