package com.karimwahdan.rsv.cards.productCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.karimwahdan.rsm.cart.CartItem
import com.karimwahdan.rsm.product.Product

@Composable
fun ProductCardHorizontal(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    borderColor: Color,
    productCardProductNameColor: Color,
    productCardProductDescriptionColor: Color,
    productCardProductPriceColor: Color,
    productCardProductDiscountPriceColor: Color,
    placeHolderImage: Int,
    errorImage: Int,
    product: Product,
    cartItem:CartItem?,
    quantity:MutableIntState,
    deleteButtonContainerColor: Color,
    deleteButtonBorderColor: Color,
    deleteButtonIcon: Int,
    deleteButtonClick: () -> Unit = {},
    cardClick:()->Unit={}
) {
    if (cartItem != null) {
        val productCount = cartItem.quantity
        if (productCount != null) {
            quantity.intValue = productCount
        }
    }

    Box(modifier = modifier.padding(5.dp).fillMaxWidth()
        .height(150.dp).background(backgroundColor)
        .clip(RoundedCornerShape(5.dp)).border(1.dp, borderColor, RoundedCornerShape(5.dp)))
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
                .clickable {
                    cardClick.invoke()
                    /*
                    TODO: copy the code below in card click
                    sharedViewModel.selectProduct(product)
                    sharedViewModel.selectProductId(product.id)
                    navController.navigate(ProductShow.route)
                     */
                }
        ) {
            HCardProductImage(placeHolderImage,errorImage,product)
            Column(modifier = Modifier.fillMaxWidth().weight(1f))
            {
                HCardProductName(productCardProductNameColor,product)
                HCardProductDescription(productCardProductDescriptionColor,product)
                HCardProductDiscountPrice(productCardProductDiscountPriceColor,product)
                HCardProductOriginalPrice(Modifier.weight(1f),productCardProductPriceColor,product)
                //QuantityValue(quantity)
            }
        }
        if (quantity.intValue > 0) {
            if (cartItem!=null){
                val custom=cartItem.productCustom
                if (custom!=null){
                    ProductDelete(deleteButtonContainerColor,deleteButtonBorderColor,deleteButtonIcon,deleteButtonClick)
                }else{
                    ProductDelete(deleteButtonContainerColor,deleteButtonBorderColor,deleteButtonIcon,deleteButtonClick)
                }
            }
        }
    }

}

@Composable
fun HCardProductImage(placeHolderImage:Int,errorImage:Int,product: Product) {
    Box {
        Image(
            rememberAsyncImagePainter(
                ImageRequest
                    .Builder(LocalContext.current)
                    .data(product.image)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(placeHolderImage)
                            .error(errorImage)
                    })
                    .scale(scale = Scale.FILL).build()
            ),
            modifier = Modifier
                .width(140.dp)
                .height(140.dp)
                .padding(5.dp),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}

@Composable
fun HCardProductOriginalPrice(modifier: Modifier,productCardProductPriceColor:Color, product: Product) {
    Text(
        color = productCardProductPriceColor,
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxHeight(),
        fontSize = 10.sp,
        textDecoration = TextDecoration.LineThrough,
        fontStyle = FontStyle.Normal,
        text = "${product.price}$"
    )
}

@Composable
fun HCardProductDiscountPrice(productCardProductDiscountPriceColor:Color,product: Product) {
    Text(
        overflow = TextOverflow.Ellipsis,
        softWrap = true,
        modifier = Modifier
            .padding(horizontal = 2.dp),
        text = "${product.discount_price}$",
        color= productCardProductDiscountPriceColor
    )
}

@Composable
fun HCardProductDescription(productCardProductDescriptionColor:Color,product: Product) {
    Text(
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp),
        softWrap = true,
        maxLines = 1,
        fontSize = 12.sp,
        overflow = TextOverflow.Ellipsis,
        text = product.description ?: "",
        color= productCardProductDescriptionColor
    )
}

@Composable
fun HCardProductName(productCardProductNameColor:Color,product: Product) {
    Text(
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp),
        softWrap = true,
        maxLines = 1,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        overflow = TextOverflow.Ellipsis,
        text = product.name ?: "",
        color = productCardProductNameColor
    )
}
