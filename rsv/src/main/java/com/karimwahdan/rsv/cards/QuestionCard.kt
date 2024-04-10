package com.karimwahdan.rsv.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CollapsableVerticalCard(question:String,answer:String){
    val showAnswer = remember { mutableStateOf(false) }
    val background=Color.White
    val questionColor=Color.Black
    val answerColor=Color.Black
    val borderColor= Color.Gray
    val cardShape= RoundedCornerShape(5.dp)
    val column1Modifier= Modifier
        .padding(5.dp).clip(cardShape)
        .border(1.dp, borderColor, cardShape)
        .background(background, cardShape)
        .fillMaxWidth()

    val questionModifier= Modifier.padding(5.dp).fillMaxWidth()
    val answerModifier= Modifier.padding(5.dp).fillMaxWidth()
    val fontWeight= FontWeight.Bold

    Column(modifier = column1Modifier) {
        Text(
            modifier = questionModifier
                .clickable {
                    showAnswer.value = !showAnswer.value
                },
            fontWeight = fontWeight,
            text = question,
            color=questionColor
        )
        if (showAnswer.value) {
            Text(
                modifier =answerModifier,
                text = answer,color= answerColor)
        }

    }

}