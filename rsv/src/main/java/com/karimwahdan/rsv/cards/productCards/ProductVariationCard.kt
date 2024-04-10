package com.karimwahdan.rsv.cards.productCards

import android.annotation.SuppressLint
import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.karimwahdan.rsm.cart.Cart
import com.karimwahdan.rsm.product.Product
import com.karimwahdan.rsm.productCustom.ProductCustom
import com.karimwahdan.rsv.buttons.IncrementButtons

@Composable
fun ProductScreenCustomizedCard(
    @SuppressLint("ModifierParameter")
    incrementButtonsModifier: Modifier = Modifier,
    incrementButtonBackgroundColor: Color,
    quantityColor: Color,
    incrementButtonColors: ButtonColors,
    incrementIcon: Int,
    decrementButtonColors: ButtonColors,
    decrementIcon: Int,
    quantity: MutableIntState,

    productLengthLabel:String,
    productWidthLabel:String,
    productHeightLabel:String,
    productWeightLabel:String,
    productThicknessLabel:String,
    productColorLabel:String,
    productMaterialLabel:String,
    productSizeLabel:String,
    placeHolderImage: Int,
    errorImage: Int,

    addToCardButtonEnabledColor:Int,
    addToCardButtonDisabledColor:Int,
    addToCartButtonLabel:String,
    cart: Cart?,
    product: Product,
    custom: ProductCustom,
    addToCartClick:()->Unit={},
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit
) {
    if (cart!=null){
        Log.e("productScreenParts","A cart is not null")
        val items=cart.items
        if (items!=null){
            Log.e("productScreenParts","B items is not null")
            val item=items.find { it.product==product && it.productCustom==custom }
            if (item!=null){
                Log.e("productScreenParts","C item found")
                quantity.intValue=item.quantity?:0
            }
        }
    }
    /*
        if (cartItem!=null){if (cartItem.productCustom!=null){if (cartItem.productCustom==custom){quantity.value=cartItem.quantity?:0}else{Log.e("productScreenParts","not == custom")}}else{
            Log.e("productScreenParts","cartItem.productCustom == null")
        }}else{
        Log.e("productScreenParts","cartItem == null")
    }

     */
    /*
    val cartItem = remember { mutableStateOf(sharedVM.getCartItem(product)) }
     if (cart != null) {
    val items = cart.items
      if (items != null) {
          cartItem.value =items.find { it.custom_id == custom.product_id && it.product_id == product.id }
          val c = cartItem.value
          if (c != null) {c.quantity = quantity.value}
      }
      }
     */


    val name = custom.name
    val width = custom.width
    val length = custom.length
    val height = custom.height
    val thickness = custom.thickness
    val weight = custom.weight
    val color = custom.color
    val material = custom.material
    val size = custom.size
    val available = custom.available

    val image: String? = if (custom.image != "#") {
        custom.image
    } else {
        product.image
    }
    Column(  modifier = Modifier
        .border(
            color = Color.Gray,
            width = 1.dp,
            shape = RoundedCornerShape(10.dp)
        )
        .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {

        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CustomCardImage(placeHolderImage, errorImage,image)
                if (available != null) {
                    if (available == 1) {
                        when {
                            quantity.intValue == 0 -> {
                                Button(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(25.dp)
                                    .padding(horizontal = 10.dp),
                                    contentPadding = PaddingValues(vertical = 0.dp, horizontal = 20.dp),
                                    shape = RoundedCornerShape(5.dp),
                                    colors = ButtonColors(
                                        contentColor = Color.White,
                                        containerColor = colorResource(addToCardButtonEnabledColor),
                                        disabledContentColor = Color.White,
                                        disabledContainerColor = colorResource(addToCardButtonDisabledColor)
                                    ), onClick = addToCartClick
                                /*
                                TODO:addToCartClick code
                                 {
                                        quantity.intValue++
                                        Log.e("productScreenParts","on add new")
                                        sharedVM.updateCartItem(product, custom, quantity.intValue)
                                    }
                                 */
                                ) {
                                    Text(text = addToCartButtonLabel)
                                }
                            }

                            quantity.intValue > 0 -> {
                                Log.e("ProductScreenParts", "q val > 0")

                                IncrementButtons(
                                    backgroundColor = incrementButtonBackgroundColor,
                                    quantityColor = quantityColor,
                                    incrementButtonColors = incrementButtonColors,
                                    incrementIcon = incrementIcon,
                                    decrementButtonColors = decrementButtonColors,
                                    decrementIcon=decrementIcon,
                                    modifier=incrementButtonsModifier,
                                    quantity = quantity,
                                    onIncrementClick =onIncrementClick,
                                    /*
                                    TODO: onIncrement code
                                     {
                                        quantity.intValue++
                                        Log.e("QuantityIntValue","${quantity.intValue}")
                                        sharedVM.updateCartItem(product, custom, quantity.intValue)
                                        }
                                        onDecrement code
                                     {
                                        quantity.intValue--
                                        sharedVM.updateCartItem(product, custom, quantity.intValue)
                                        }
                                     */
                                    onDecrementClick =onDecrementClick)
                            }
                        }
                    }
                }
            }
            Column {
                if (name != null) {
                    CustomCardText("", name)
                }
                if (width != null) {
                    CustomCardText(productWidthLabel, "$width cm")
                }
                if (length != null) {
                    CustomCardText(productLengthLabel, "$length cm")
                }
                if (height != null) {
                    CustomCardText(productHeightLabel, "$height cm")
                }
                if (thickness != null) {
                    CustomCardText(productThicknessLabel, "$thickness cm")
                }
                if (weight != null) {
                    CustomCardText(productWeightLabel, "$weight gm")
                }
                if (color != null) {
                    CustomCardColor(productColorLabel, color)
                }
                if (material != null) {
                    CustomCardText(productMaterialLabel, material)
                }
                if (size != null) {
                    CustomCardText(productSizeLabel, size)
                }


            }
        }
    }
}

@Composable
fun CustomCardImage(placeHolderImage:Int,errorImage:Int,image: String?) {
    val context = LocalContext.current
    Image(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        painter = rememberAsyncImagePainter(
            ImageRequest
                .Builder(context)
                .data(image)
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(placeHolderImage)
                        .error(errorImage)
                }).scale(scale = Scale.FILL).build()
        ), contentDescription = null
    )
}
@Composable
fun CustomCardText(s: String, s1: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$s:")
        Text(text = s1)
    }
}
@Composable
fun CustomCardColor(s: String, color: String) {
    val parsedColor = parseColor(color)
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = s)
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(20.dp)
                .background(color = Color(parsedColor))
        )
    }
}