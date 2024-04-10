package com.karimwahdan.rsv.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.karimwahdan.rsm.cart.CartItem
import com.karimwahdan.rsv.VerticalSpacer

@Composable
fun MiniCartItemCard(
    cartBackgroundColor:Color,
    borderColor: Color,
    cartTitleColor:Color,
    cartProductPriceColor:Color,
    cartProductTotalPriceColor:Color,
    unitPriceLabel:String,
    quantityLabel:String,
    totalPriceLabel:String,
    cartItem: CartItem,
) {

    val productCount = cartItem.quantity ?: 0
    val product=cartItem.product

    var discountPrice = 0f
    if (product!=null){
        discountPrice=product.discount_price?:0f
    }
    val total = productCount * discountPrice

    Column(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(5.dp))
            .padding(5.dp).border(1.dp, borderColor, RoundedCornerShape(5.dp))
            .background(cartBackgroundColor,RoundedCornerShape(5.dp))
    ) {
        if (product!=null){
            Text(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                fontWeight = FontWeight.Bold,
                text = product.name!!
            )
        }

        VerticalSpacer(height = 10)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    text = "${quantityLabel}: ${cartItem.quantity}",
                    color = cartTitleColor
                )
                if (product!=null){
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "${unitPriceLabel}: $ ${product.discount_price} ",
                        color= cartProductPriceColor
                    )
                }


            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = "${totalPriceLabel}: $ $total ",
                    modifier = Modifier.padding(horizontal = 5.dp),
                    color= cartProductTotalPriceColor
                )
            }
        }


    }
}