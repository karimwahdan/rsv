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
import com.karimwahdan.rsm.privacyPolicy.PrivacyPolicy

@Composable
fun PrivacyPolicyCard(
    backgroundColor:Color,
    borderColor: Color,
    questionColor:Color,
    answerColor:Color,
    policy: PrivacyPolicy) {
    val showAnswer = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(5.dp).clip(RoundedCornerShape(5.dp))
            .border(1.dp, borderColor, RoundedCornerShape(5.dp))
            .background(backgroundColor,RoundedCornerShape(5.dp)).fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable {
                    showAnswer.value = !showAnswer.value
                },
            fontWeight = FontWeight.Bold,
            text = policy.title,
            color=questionColor
        )
        if (showAnswer.value) {
            Text(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                text = policy.content,
                color= answerColor
            )
        }
    }
}
