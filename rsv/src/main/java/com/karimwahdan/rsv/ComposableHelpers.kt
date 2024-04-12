package com.karimwahdan.rsv

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun colorParser(colorStr:String?): Color {
    return if (colorStr!=null){Color(android.graphics.Color.parseColor(colorStr))}else{Color.White}
}

@Composable
fun VerticalSpacer(height: Int) {Spacer(modifier = Modifier.height(height.dp))}

@Composable
fun HorizontalSpacer(width: Int) {Spacer(modifier = Modifier.width(width.dp))}

@Composable
fun DeleteButton(containerColor:Color,borderColor: Color,icon:Int,onClick: () -> Unit) {
    Button(
        shape = RoundedCornerShape(5.dp),
        colors = ButtonColors(
            containerColor = containerColor,
            disabledContainerColor = borderColor,
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        modifier = Modifier
            .width(28.dp)
            .height(25.dp)
            .padding(horizontal = 1.dp),
        contentPadding = PaddingValues(
            vertical = 0.dp,
            horizontal = 0.dp
        ), onClick = onClick
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null
        )
    }
}

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String = "",
    textAlign: TextAlign = TextAlign.Center,
    softWarp: Boolean = true,
    maxLines: Int = 1,
    fontSize:Int=12,
    overflow: TextOverflow = TextOverflow.Ellipsis
) {
    Text(
        textAlign = textAlign,
        modifier = modifier.padding(horizontal = 2.dp),
        softWrap = softWarp,
        maxLines = maxLines,
        fontSize = fontSize.sp,
        overflow = overflow,
        text = text
    )
}

@Composable
fun CustomCircle(modifier:Modifier,color: Color, radius: Float) {
    Canvas(
        modifier = modifier
            .size((radius * 2).dp)
            .padding(8.dp)
    ) {
        drawCircle(color = color, radius = radius, style = Stroke(4.dp.toPx()))
    }
}

@Composable
fun CustomLine(color: Color,modifier: Modifier) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(2.dp)
            .padding(vertical = 8.dp)
    ) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = 1f,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun QuantityValue(color: Color, value: MutableIntState) {
    val modifier=Modifier.padding(horizontal = 25.dp)
    Text(modifier = modifier,text = value.intValue.toString(),color = color)
}