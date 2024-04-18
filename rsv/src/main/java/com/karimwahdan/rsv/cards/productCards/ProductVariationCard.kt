package com.karimwahdan.rsv.cards.productCards

import android.annotation.SuppressLint
import android.graphics.Color.parseColor
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.karimwahdan.rsm.cart.Cart
import com.karimwahdan.rsm.product.Product
import com.karimwahdan.rsm.productCustom.ProductCustom
import com.karimwahdan.rsv.buttons.IncrementButtons
import com.karimwahdan.rsv.colorParser

@Composable
fun ProductScreenCustomizedCard(
    @SuppressLint("ModifierParameter")
    incrementButtonsModifier: Modifier = Modifier,
    incrementButtonBackgroundColor: Color,
    quantityColor: Color,
    incrementButtonColors: ButtonColors,
    incrementIcon: Int?,
    decrementButtonColors: ButtonColors,
    decrementIcon: Int?,
    quantity: MutableIntState,

    productLengthLabel:String,
    productWidthLabel:String,
    productHeightLabel:String,
    productWeightLabel:String,
    productThicknessLabel:String,
    productColorLabel:String,
    productMaterialLabel:String,
    productSizeLabel:String,
    placeHolderImage: Int?,
    errorImage: Int?,

    addToCardButtonEnabledColor:Color,
    addToCardButtonDisabledColor:Color,
    addToCartButtonLabel:String,
    cart: Cart?,
    product: Product,
    custom: ProductCustom,
    addToCartClick:()->Unit,
    onIncrementClick: () -> Unit,
    onDecrementClick: () -> Unit
) {
    if (cart!=null){
        val items=cart.items
        if (items!=null){
            val item=items.find { it.product==product && it.productCustom==custom }
            if (item!=null){quantity.intValue=item.quantity?:0}
        }
    }

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

    val image: String? = if (custom.image != "#") {custom.image} else {product.image}
    Row(
        modifier = Modifier
            .padding(5.dp)
            .border(color = Color.Gray, width = 1.dp, shape = RoundedCornerShape(10.dp))
            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CustomCardImage(placeHolderImage, errorImage,image)
            if (available != null) {
                if (available == 1) {
                    when {
                        quantity.intValue == 0 -> {
                            Button(modifier = Modifier
                                //.fillMaxWidth()
                                .height(25.dp)
                                .padding(horizontal = 10.dp),
                                contentPadding = PaddingValues(vertical = 0.dp, horizontal = 20.dp),
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonColors(
                                    contentColor = Color.White,
                                    containerColor = addToCardButtonEnabledColor,
                                    disabledContentColor = Color.White,
                                    disabledContainerColor = addToCardButtonDisabledColor
                                ), onClick = addToCartClick

                            ) {
                                Text(text = addToCartButtonLabel)
                            }
                        }

                        quantity.intValue > 0 -> {

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

                                onDecrementClick =onDecrementClick)
                        }
                    }
                }
            }
        }
        Column(modifier= Modifier
            .padding(5.dp)) {
            if (name != null) {CustomCardText("", name)}
            if (width != null) {CustomCardText(productWidthLabel, "$width cm")}
            if (length != null) {CustomCardText(productLengthLabel, "$length cm")}
            if (height != null) {CustomCardText(productHeightLabel, "$height cm")}
            if (thickness != null) {CustomCardText(productThicknessLabel, "$thickness cm")}
            if (weight != null) {CustomCardText(productWeightLabel, "$weight gm")}
            if (color != null) {CustomCardColor(productColorLabel, color)}
            if (material != null) {CustomCardText(productMaterialLabel, material)}
            if (size != null) {CustomCardText(productSizeLabel, size)}
        }
    }

}

@Composable
fun CustomCardImage(placeHolderImage:Int?,errorImage:Int?,image: String?) {
    val context = LocalContext.current
    if (placeHolderImage!=null && errorImage!=null){
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

}
@Composable
fun CustomCardText(s: String, s1: String) {
    val label: String = if (s!=""){"$s:"}else{"";}
    Row(
        modifier = Modifier.fillMaxWidth().padding(2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(modifier=Modifier.padding(1.dp),text = label)
        Text(modifier=Modifier.padding(1.dp),text = s1)
    }
}
@Composable
fun CustomCardColor(s: String, color: String) {
    val parsedColor = parseColor(color)
    Row(
        modifier = Modifier.fillMaxWidth().padding(2.dp),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier=Modifier.padding(1.dp),text = "$s:")
        Box(
            modifier = Modifier
                .width(50.dp)
                .padding(1.dp)
                .height(20.dp)
                .border(width = 1.dp,color=Color.Gray, shape = RoundedCornerShape(5.dp))
                .background(color = Color(parsedColor),shape = RoundedCornerShape(5.dp))
        )
    }
}
/*

@Preview
@Composable
fun ProductVCardPreview(){
    val q= remember {mutableIntStateOf(1)}
    val custom=ProductCustom(1,1,
        1,"Test Custom",1f,1f,
        1f,1f,1f,"#6EFF2C",
        "very very very very long text ","",
        "test Size",1)
    LazyRow(modifier=Modifier.fillMaxWidth(),content = {
        item{
            ProductScreenCustomizedCard(
                incrementButtonBackgroundColor = Color.White,
                quantityColor = Color.Black,
                incrementButtonColors = ButtonColors(containerColor = Color.Green, contentColor = Color.White, disabledContainerColor = Color.Gray, disabledContentColor = Color.DarkGray),
                incrementIcon =null ,
                decrementButtonColors = ButtonColors(containerColor = Color.Red, contentColor = Color.White, disabledContainerColor = Color.Gray, disabledContentColor = Color.DarkGray),
                decrementIcon = null,
                quantity = q,
                productLengthLabel = "Length",
                productWidthLabel = "Width",
                productHeightLabel = "Height",
                productWeightLabel = "Weight",
                productThicknessLabel = "Thickness",
                productColorLabel = "Color",
                productMaterialLabel = "Material",
                productSizeLabel = "Size",
                placeHolderImage = null,
                errorImage = null,
                addToCardButtonEnabledColor = colorParser(colorStr = "#6EFF2C"),
                addToCardButtonDisabledColor = colorParser(colorStr = "#A6A6A6"),
                addToCartButtonLabel = "Add to Cart",
                cart =Cart(1,1,1,1,null) ,
                product = Product(1,1,1,1,"","","","Test Name",1,"","",1500f,1000f,0,0,0,null,null,null,null,null),
                custom=custom,
                onIncrementClick = {},
                addToCartClick={},
                onDecrementClick = {}
            )
        }

    })
}
 */