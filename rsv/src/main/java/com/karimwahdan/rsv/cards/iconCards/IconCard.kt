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
import androidx.compose.ui.draw.shadow
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
fun IconCard(
    iconBackground:Color,
    borderColor: Color,
    titleFontColor:Color,
    placeholderIcon:Int,
    errorIcon:Int,
    image: String?, text: String?,onClick:()->Unit) {

    val column1Modifier= Modifier.padding(5.dp).height(100.dp).width(72.dp)

    val column2Modifier=Modifier.clip(RoundedCornerShape(10.dp))
        .border(1.dp, borderColor,RoundedCornerShape(10.dp))
        .width(72.dp).height(72.dp)
        .background(color=iconBackground,shape = RoundedCornerShape(10.dp))
        .shadow(1.dp,shape= RoundedCornerShape(1.dp),ambientColor = Color.Gray)

    val imageModifier=Modifier.width(64.dp).height(64.dp)
    
    val textModifier=Modifier.padding(vertical = 5.dp).width(72.dp)

    val alignmentCenterH=Alignment.CenterHorizontally
    val arrangementCenter=Arrangement.Center

    Column(
        horizontalAlignment = alignmentCenterH,
        modifier = column1Modifier.clickable { onClick.invoke() }
    ) {
        Column(
            horizontalAlignment = alignmentCenterH,
            verticalArrangement = arrangementCenter,
            modifier = column2Modifier
        ) {
            Image(
                rememberAsyncImagePainter( ImageRequest
                    .Builder(LocalContext.current)
                    .data(image)
                    .apply(block =fun ImageRequest.Builder.(){
                        placeholder(placeholderIcon)
                            .error(errorIcon)})
                    .scale(scale = Scale.FILL).build()),
                modifier = imageModifier,
                contentDescription = null
            )
        }
        Text(fontSize = 11.sp,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            maxLines = 1,
            color=titleFontColor,
            modifier = textModifier,
            text = text?: ""
        )
    }
}