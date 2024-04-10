package com.karimwahdan.rsv.cards.productCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
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
import com.karimwahdan.rsv.DeleteButton

@Composable
fun ProductCard(
    cardBackgroundColor:Color,
    cardBorderColor:Color,
    deleteButtonContainerColor:Color,
    deleteButtonBorderColor:Color,
    deleteButtonIcon:Int,
    productNameColor: Color,
    priceColor: Color,
    discountPriceColor: Color,
    placeholderImage: Int,
    errorImage: Int,
    product: Product,
    cartItem:CartItem?,
    quantity:MutableIntState,
    modifier: Modifier = Modifier,
    deleteButtonClick:()->Unit={},
    productImageClick:()->Unit={},
) {
    if (cartItem != null) {
        val productCount = cartItem.quantity
        if (productCount != null) {
            quantity.intValue = productCount
        }
    }
    val productCardModifier = modifier
        .padding(5.dp).width(145.dp).height(220.dp)
        .background(color = cardBackgroundColor,shape = RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp)).border(1.dp, cardBorderColor, RoundedCornerShape(10.dp))

    val alignTopEnd = Alignment.TopEnd
    val alignCenterH = Alignment.CenterHorizontally
    val arrangeCenter = Arrangement.Center
    Box(
        contentAlignment = alignTopEnd
    ) {
        Column(
            verticalArrangement = arrangeCenter,
            horizontalAlignment = alignCenterH,
            modifier = productCardModifier
        ) {
            ProductImage(placeholderImage= placeholderImage,
                errorImage=errorImage,
                product=product, viewProductClick = productImageClick)
            ProductName(productNameColor,product)
            ProductPrice(priceColor,discountPriceColor,product)
            //QuantityValue(value = quantity)
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
/*

fun viewProduct(navController: NavController, sharedViewModel: SharedViewModel, product: Product) {
    sharedViewModel.selectProduct(product)
    sharedViewModel.selectProductId(product.id)
    navController.navigate(ProductShow.route)
}
 */

/*
@Composable
fun ProductAddToCartButton(
    onClick: () -> Unit
) {
    val projectLang= Preferences.LanguageSettings().getButtonLanguage()
    val modifier= Modifier
        .padding(vertical = 10.dp, horizontal = 5.dp)
        .fillMaxWidth()
        .height(25.dp)
    val disabledColor=colorResource(id = R.color.gray)
    val buttonColors=ButtonColors(
        containerColor = addToCartButtonBackground(),
        contentColor = addToCartButtonFontColor(),
        disabledContentColor = addToCartButtonFontColor(),
        disabledContainerColor = disabledColor,
    )
    val shape=RoundedCornerShape(3.dp)
    val textModifier=Modifier.padding(0.dp)
    val buttonText=projectLang.addToCartButtonText
  /*
    Button(
        shape = shape,colors = buttonColors,
        modifier =modifier,onClick = onClick,
        contentPadding = PaddingValues(5.dp,0.dp),
        content = {Text(modifier = textModifier,text = buttonText)}
    )
   */
}
 */

@Composable
fun ProductDelete(containerColor: Color,borderColor: Color,
                  icon:Int,click:()->Unit={}) {
    Box(modifier = Modifier.padding(10.dp)) {
        DeleteButton(containerColor,borderColor,icon) {
            click.invoke()
          /*
          TODO: the deleteButton click function
           .
           sharedViewModel.updateQuantity(product,productCustom, 0)
            val cart=sharedViewModel.viewCart()
            if (cart!=null){
                val items=cart.items
                if (items!=null){
                    val cartItem=items.find { it.product_id==product.id }
                    if (cartItem!=null){
                        sharedViewModel.deleteCartItem(cartItem)
                    }
                }
            }
            sharedViewModel.updateTotalItems()
            Preferences.CartItems().removeCartItem(product)
            sharedViewModel.setProductQuantity(product, 0)
           */
        }
    }
}

@Composable
fun ProductPrice(priceColor:Color,discountPriceColor:Color,product: Product) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            maxLines = 1,
            modifier = Modifier
                .padding(horizontal = 2.dp)
                .fillMaxWidth()
                .weight(1f),
            text = "${product.discount_price}$",
            color= discountPriceColor
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            fontSize = 10.sp,
            maxLines = 1,
            textDecoration = TextDecoration.LineThrough,
            fontStyle = FontStyle.Normal,
            text = "${product.price}$",
            color= priceColor
        )
    }
}

@Composable
fun ProductName(productNameColor: Color, product: Product) {
    Text(
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 2.dp),
        softWrap = true,
        maxLines = 1,
        fontSize = 13.sp,
        overflow = TextOverflow.Ellipsis,
        text = product.name ?: "",
        color= productNameColor
    )
}

@Composable
fun ProductImage(placeholderImage:Int,errorImage:Int,product: Product,viewProductClick:()->Unit={}) {
    Image(
        rememberAsyncImagePainter(
            ImageRequest
                .Builder(LocalContext.current).data(product.image)
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(placeholderImage).error(errorImage)
                })
                .scale(scale = Scale.FIT).build()
        ),
        modifier = Modifier.width(140.dp).height(130.dp).padding(5.dp)
            .clickable {
                viewProductClick.invoke()
                /*
                TODO: the code for view product
                 */
                //viewProduct(navController, sharedViewModel, product)
                       },
        contentScale = ContentScale.Fit,
        contentDescription = null,
    )
}
