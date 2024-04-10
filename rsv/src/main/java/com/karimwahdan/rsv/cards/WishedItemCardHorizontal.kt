package com.karimwahdan.rsv.cards

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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.karimwahdan.rsm.wishlists.WishItem

@Composable
fun WishedItemCardHorizontal(
    deleteButtonText:String,
    cardBackgroundColor:Color,
    borderColor:Color,
    productNameColor: Color,
    productDescriptionColor: Color,
    placeholderImage: Int,
    errorImage: Int,
    item: WishItem, modifier: Modifier = Modifier,
    cardClick:()->Unit={},
    deleteButtonClick:()->Unit={}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(5.dp).fillMaxWidth().height(150.dp)
            .background(cardBackgroundColor).clip(RoundedCornerShape(5.dp))
            .border(1.dp, borderColor,RoundedCornerShape(5.dp)
            )
            .clickable {
                cardClick.invoke()
                /*
                TODO: this is what to write in the card Click
                 navController.navigate(ProductShow.route)

                 */
            }
    ) {
        HCardWishedItemImage(placeholderImage,errorImage,item)
        Column(modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            HCardWishedItemName(productNameColor,item)
            HCardWishedItemDescription(productDescriptionColor,item)
            Button(onClick =deleteButtonClick
                /*
                 TODO: this is what delete button click should invoke
                  sharedVM.deleteWishlistItem(item)
                 */
            ) {Text(text = deleteButtonText)}
        }
    }
}

@Composable
fun HCardWishedItemImage(placeholderImage:Int,errorImage:Int,item: WishItem) {
    val modifier= Modifier
        .width(140.dp)
        .height(140.dp)
        .padding(5.dp)
    val scale=Scale.FILL
    val contentScale=ContentScale.Crop
    val product=item.product
    val context=LocalContext.current
    if (product!=null){
        Box {
            Image(
                rememberAsyncImagePainter(
                    ImageRequest
                        .Builder(context)
                        .data(product.image)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(placeholderImage).error(errorImage)
                        }).scale(scale).build()
                ),
                modifier = modifier,
                contentScale = contentScale,
                contentDescription = null,
            )
        }

    }
}

@Composable
fun HCardWishedItemDescription(productDescriptionColor:Color,item: WishItem) {
    val product=item.product
    val text=if (product!=null){product.description?: ""}else{""}
    val modifier=Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
    val align=TextAlign.Center
    val ellipsis=TextOverflow.Ellipsis
    val sp12=12.sp
    Text(
        textAlign = align,
        modifier = modifier,
        softWrap = true,
        maxLines = 1,
        fontSize = sp12,
        overflow = ellipsis,
        text = text,
        color= productDescriptionColor
    )
}

@Composable
fun HCardWishedItemName(productNameColor:Color,item: WishItem) {
    val product=item.product
    val text=if (product!=null){product.name ?: ""}else{""}
    val align=TextAlign.Center
    val ellipsis=TextOverflow.Ellipsis
    val modifier=Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
    val bold=FontWeight.Bold
    Text(
        textAlign = align,modifier = modifier,
        softWrap = true,maxLines = 1,fontSize = 18.sp,
        fontWeight = bold,text = text,overflow = ellipsis,
        color = productNameColor
    )
}

