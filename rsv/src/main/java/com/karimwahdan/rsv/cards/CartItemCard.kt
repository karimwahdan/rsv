package com.karimwahdan.rsv.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.karimwahdan.rsm.cart.CartItem
import com.karimwahdan.rsm.product.Product
import com.karimwahdan.rsv.VerticalSpacer
import com.karimwahdan.rsv.buttons.IncrementButtons

@Composable
fun CartItemCard(
    @SuppressLint("ModifierParameter") incrementButtonsModifier: Modifier = Modifier,
    incrementButtonBackgroundColor: Color,
    incrementButtonColors: ButtonColors,
    incrementIcon: Int?,
    decrementButtonColors: ButtonColors,
    decrementIcon: Int?,
    deleteButtonLabel:String,
    saveForLaterButtonLabel:String,
    backgroundColor:Color,
    borderColor:Color,
    deleteButtonColor:Color,
    saveForLaterButtonColor:Color,
    cartTitleColor: Color,
    cartProductTotalPriceColor:Color,
    cartProductDiscPriceColor: Color,
    cartProductPriceColor:Color,
    quantityColor: Color,
    placeholderImage: Int,
    errorImage: Int,
    product: Product,
    cartItem:CartItem?,
    quantity:MutableIntState,
    deleteClick:()->Unit={},
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit) {
    var totalProductDiscountPrice by remember { mutableFloatStateOf(0f) }
    if (cartItem != null) {
        val productCount = cartItem.quantity
        if (productCount != null) {
            quantity.intValue = productCount
            totalProductDiscountPrice = quantity.intValue * (product.discount_price ?: 0f)
        }
    }
    if (quantity.intValue > 0) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .padding(5.dp)
                .border(1.dp, borderColor, RoundedCornerShape(5.dp))
                .background(backgroundColor, RoundedCornerShape(5.dp))
        ) {
            Row {
                if (cartItem != null) {
                    CartItemCardImage(placeholderImage,errorImage,cartItem,Modifier.align(Alignment.CenterVertically))
                }
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 5.dp)
                ) {
                    if (cartItem != null) {
                        CartItemProductName(cartTitleColor,cartItem)
                        CartItemProductCustomName(cartTitleColor,cartItem)
                        Row(
                            verticalAlignment =
                            Alignment.CenterVertically
                        ) {
                            CartItemProductDiscountPrice(cartProductDiscPriceColor,cartItem)
                            CartItemProductPrice(cartProductPriceColor,cartItem)
                        }
                    }
                    VerticalSpacer(height = 10)
                    CartItemProductTotalPrice(cartProductTotalPriceColor,totalProductDiscountPrice)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                IncrementButtons(
                    modifier = incrementButtonsModifier,
                    backgroundColor =incrementButtonBackgroundColor ,
                    quantityColor = quantityColor,
                    incrementButtonColors = incrementButtonColors,
                    incrementIcon = incrementIcon,
                    decrementButtonColors = decrementButtonColors,
                    decrementIcon = decrementIcon,
                    quantity = quantity,
                    onIncrementClick = onIncrementClick,
                    onDecrementClick = onDecrementClick)
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    if (cartItem != null) {
                        Text(modifier = Modifier.clickable {
                            deleteClick.invoke()
                        },
                            text = deleteButtonLabel,color=deleteButtonColor)
                    }

                    Text(text = saveForLaterButtonLabel,color=saveForLaterButtonColor)
                }
            }
        }
    }
}

@Composable
fun CartItemProductTotalPrice(cartProductTotalPriceColor:Color,total: Float) {
    Text(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        text = "$total $",
        color=cartProductTotalPriceColor
    )
}

@Composable
fun CartItemProductPrice(cartProductPriceColor:Color,cartItem: CartItem) {
    val product=cartItem.product
    if (product!=null){
        Text(fontSize = 11.sp,color = cartProductPriceColor,textDecoration = TextDecoration.LineThrough,text = "${product.price?:0}$")
    }

}

@Composable
fun CartItemProductDiscountPrice(cartProductDiscPriceColor:Color,cartItem: CartItem) {
    val product=cartItem.product
    if (product!=null){
        Text(text = "${product.discount_price?:0}$",modifier = Modifier.padding(horizontal = 5.dp),color=cartProductDiscPriceColor)
    }
}

@Composable
fun CartItemProductName(cartTitleColor:Color,cartItem: CartItem) {
    val product=cartItem.product
    if (product!=null){
        Text(modifier = Modifier.fillMaxWidth(),fontWeight = FontWeight.Bold,text = product.name?:"",color= cartTitleColor)
    }
}

@Composable
fun CartItemProductCustomName(cartTitleColor:Color,cartItem: CartItem) {
    val custom=cartItem.productCustom
    if (custom!=null){
        Text(modifier = Modifier.fillMaxWidth(),fontWeight = FontWeight.Bold,text = custom.name?:"",color= cartTitleColor)
    }
}

@Composable
fun CartItemCardImage(placeholderImage:Int,errorImage:Int,cartItem: CartItem, modifier: Modifier = Modifier) {
    val product=cartItem.product
    val custom=cartItem.productCustom
    if (custom!=null){
        Image(
            rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(custom.image)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(placeholderImage)
                            .error(errorImage)
                    })
                    .scale(scale = Scale.FIT)
                    .build()
            ),
            modifier = modifier
                .width(115.dp)
                .padding(5.dp),
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
    }else{
        if (product!=null){
            Image(
                rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(product.image)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(placeholderImage)
                                .error(errorImage)
                        })
                        .scale(scale = Scale.FIT)
                        .build()
                ),
                modifier = modifier
                    .width(115.dp)
                    .padding(5.dp),
                contentScale = ContentScale.Fit,
                contentDescription = null
            )
        }

    }

}
