package com.karimwahdan.rsv.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.karimwahdan.rsv.QuantityValue


@Composable
fun IncrementButtons(
    backgroundColor: Color,
    quantityColor: Color,
    incrementButtonColors: ButtonColors,
    incrementIcon: Int,
    decrementButtonColors: ButtonColors,
    decrementIcon: Int,
    modifier: Modifier = Modifier,
    quantity: MutableIntState,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(3.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IncrementButton(icon = incrementIcon, buttonColors = incrementButtonColors, onClick = onIncrementClick)
        QuantityValue(color = quantityColor, value = quantity)
        DecrementButton(icon = decrementIcon, buttonColors = decrementButtonColors, onClick = onDecrementClick)
    }
}


@Composable
fun IncrementButton(
    icon:Int,
    buttonColors: ButtonColors,
    onClick: () -> Unit
) {
    val paddingValues=PaddingValues(vertical = 0.dp,horizontal = 0.dp)
    val modifier=Modifier.width(28.dp).height(25.dp).padding(horizontal = 1.dp)
    val shape=RoundedCornerShape(3.dp)
    Button(
        shape = shape,colors =buttonColors,modifier = modifier,
        contentPadding = paddingValues, onClick = onClick
    ) {
        Image(painter = painterResource(id = icon),contentDescription = null)
    }
}



@Composable
fun DecrementButton( icon:Int,
                     buttonColors: ButtonColors,
                     onClick: () -> Unit) {
    val paddingValues=PaddingValues(vertical = 0.dp,horizontal = 0.dp)
    val modifier=Modifier.width(28.dp).height(25.dp).padding(horizontal = 1.dp)
    val shape=RoundedCornerShape(3.dp)
    Button(
        shape = shape,colors =buttonColors,modifier = modifier,
        contentPadding = paddingValues, onClick = onClick
    ) {
        Image(painter = painterResource(id = icon),contentDescription = null)
    }
}


