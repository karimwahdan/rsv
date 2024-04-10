package com.karimwahdan.rsv

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

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
