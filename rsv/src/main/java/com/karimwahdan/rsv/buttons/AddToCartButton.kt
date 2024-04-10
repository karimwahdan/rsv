package com.karimwahdan.rsv.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddToCartButton(
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    buttonText: String = "Add To Cart",
    addToCartTextColor: Color = Color.Black,
    onClick: () -> Unit = {/*ToDo */ }
) {
    val modifier = Modifier.padding(5.dp).height(25.dp).fillMaxWidth()
    val paddingValues = PaddingValues(vertical = 0.dp, horizontal = 5.dp)
    Button(shape = RoundedCornerShape(3.dp),
        colors = buttonColors,
        modifier = modifier,
        contentPadding = paddingValues, onClick = onClick,
        content = { AddToCartButtonText(buttonText, addToCartTextColor) }
    )
}

@Composable
fun AddToCartButtonText(buttonText: String, addToCartTextColor: Color) {
    val modifier = Modifier.padding(0.dp)
    Text(modifier = modifier, text = buttonText, color = addToCartTextColor)
}