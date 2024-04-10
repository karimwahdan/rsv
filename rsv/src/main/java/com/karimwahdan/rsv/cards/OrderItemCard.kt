package com.karimwahdan.rsv.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.karimwahdan.rsm.orders.OrderItem
import com.karimwahdan.rsv.VerticalSpacer

@Composable
fun OrderItemCard(
    loadingImage: Int,
    placeholderImage: Int,
    errorImage: Int,
    expiryDateLabel: String,
    originalPriceLabel: String,
    quantityLabel: String,
    totalLabel: String,
    item: OrderItem) {
    val cardModifier = Modifier.fillMaxWidth().padding(10.dp)
        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
    val columnModifier = Modifier.fillMaxWidth().padding(5.dp)
    Row(modifier = cardModifier) {
        OrderItemImage(loadingImage, placeholderImage, errorImage, item)
        Column(modifier = columnModifier.weight(1f)) {
            OrderItemCardProductName(item)
            VerticalSpacer(height = 10)
            OrderItemCardExpiryDate(expiryDateLabel,item)
            VerticalSpacer(height = 10)
            OrderItemCardPrice(originalPriceLabel,item)
            OrderItemCardQuantity(quantityLabel,item)
            VerticalSpacer(height = 10)
            OrderItemCardTotalPrice(totalLabel,item)
        }
    }
}

@Composable
fun OrderItemCardProductName(orderItem: OrderItem) {
    val product = orderItem.product
    if (product != null) {
        Text(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            text = product.name!!
        )
    }
}

@Composable
fun OrderItemCardTotalPrice(totalLabel: String, item: OrderItem) {
    val quantity = item.quantity ?: 0
    val price = item.price ?: 0f
    val totalPrice = quantity.toFloat() * price
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${totalLabel}:")
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp, text = "$totalPrice $"
        )
    }
}

@Composable
fun OrderItemCardQuantity(quantityLabel: String, item: OrderItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${quantityLabel}:")
        Text(text = "${item.quantity}")
    }
}

@Composable
fun OrderItemCardPrice(originalPriceLabel: String, item: OrderItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${originalPriceLabel}:")
        Text(fontWeight = FontWeight.Bold, text = "${item.price ?: 0} $")
    }
}

@Composable
fun OrderItemCardExpiryDate(expiryDateLabel: String, orderItem: OrderItem) {
    val expiryDate = orderItem.expiry_date
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (expiryDate != null) {
            Text(text = "${expiryDateLabel}:")
            Text(text = expiryDate)
        }
    }
}

@Composable
fun OrderItemImage(
    loadingImage: Int,
    placeholderImage: Int,
    errorImage: Int,
    item: OrderItem
) {
    val modifier = Modifier.width(140.dp)
        .height(140.dp).padding(5.dp)
    val crop = ContentScale.Crop
    val product = item.product
    if (product != null) {
        val image = product.image
        if (image == null) {
            Box {
                Image(
                    modifier = modifier, contentScale = crop, contentDescription = null,
                    painter = painterResource(loadingImage),
                )
            }
        } else {
            Box {
                Image(
                    rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(product.image).apply(
                                block = fun ImageRequest.Builder.() {
                                    placeholder(placeholderImage).error(errorImage)
                                }).scale(scale = Scale.FILL).build()
                    ),
                    modifier = Modifier.width(140.dp).height(140.dp).padding(5.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
        }
    }

}
