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
import com.karimwahdan.rsm.aboutUs.AboutUs

@Composable
fun AboutUsCard(background: Color,
                questionColor:Color,
                answerColor:Color,
                borderColor:Color,
                aboutUs: AboutUs) {
    val showAnswer = remember { mutableStateOf(false) }

    val cardShape=RoundedCornerShape(5.dp)
    val column1Modifier= Modifier
        .padding(5.dp).clip(cardShape)
        .border(1.dp, borderColor, cardShape)
        .background(background, cardShape)
        .fillMaxWidth()


    val questionModifier= Modifier
        .padding(5.dp)
        .fillMaxWidth()
    val answerModifier= Modifier
        .padding(5.dp)
        .fillMaxWidth()
    val fontWeight=FontWeight.Bold

    val answer=aboutUs.content
    val title=aboutUs.title

    Column(modifier = column1Modifier) {
        Text(
            modifier = questionModifier
                .clickable {
                    showAnswer.value = !showAnswer.value
                },
            fontWeight = fontWeight,
            text = title,
            color=questionColor
        )
        if (showAnswer.value) {
            Text(
                modifier =answerModifier,
                text = answer,color= answerColor)
        }

    }
}