package com.karimwahdan.rsv.cards.iconCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale

@Composable
fun LargeIconCard(
    titleFontSize:Int,
    iconBackground: Color,
    borderColor: Color,
    titleFontColor: Color,
    placeholderIcon:Int,
    errorIcon:Int,
    image: String?,
    text: String?,
    onClick:()->Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp)
            .height(160.dp)
            .width(160.dp)
            .clickable { onClick.invoke() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .border(
                    1.dp, borderColor,
                    RoundedCornerShape(10.dp)
                )
                .width(130.dp)
                .height(130.dp)
                .background(iconBackground)
        ) {
            Image(
                rememberAsyncImagePainter( ImageRequest
                    .Builder(LocalContext.current)
                    .data(image)
                    .apply(block =fun ImageRequest.Builder.(){
                        placeholder(placeholderIcon)
                            .error(errorIcon)})
                    .scale(scale = Scale.FILL).build()),
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentDescription = null
            )
        }
        Text(fontSize = titleFontSize.sp,
            textAlign = TextAlign.Center,
            color = titleFontColor,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            maxLines = 1,
            modifier = Modifier
                .padding(vertical = 5.dp)
                .width(130.dp),
            text = text?: ""
        )
    }
}
