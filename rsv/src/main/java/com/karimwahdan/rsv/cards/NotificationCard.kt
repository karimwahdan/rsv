package com.karimwahdan.rsv.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karimwahdan.rsm.notifications.ProjectNotification

@Composable
fun NotificationCard(
    backgroundColor:Color,
    borderColor:Color,
    notification:ProjectNotification,
    logo:Int,
){
    Box(modifier = Modifier.padding(10.dp)){
        Column(modifier= Modifier.background(backgroundColor)
            .border(width = 1.dp,color = borderColor,shape = RoundedCornerShape(10.dp))
            .fillMaxWidth().padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier= Modifier.size(52.dp),
                    painter = painterResource(logo),
                    contentDescription =null )
                Column(modifier= Modifier.fillMaxWidth().weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween){
                    Text(
                        modifier=Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        maxLines=1,
                        overflow = TextOverflow.Ellipsis,
                        text = notification.title
                    )
                    Text(
                        modifier=Modifier.fillMaxWidth(),
                        maxLines=1,
                        overflow = TextOverflow.Ellipsis,
                        text = notification.message,
                    )
                }
            }
            Text(modifier=Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontSize = 12.sp,
                text = notification.created_at?:"")
        }
    }

  }
