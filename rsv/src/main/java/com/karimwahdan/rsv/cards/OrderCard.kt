package com.karimwahdan.rsv.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.karimwahdan.rsm.orders.Order


val modifier= Modifier.padding(5.dp).background(Color.White,shape = RoundedCornerShape(10.dp))
    .clip(RoundedCornerShape(10.dp)).border(width = 1.dp,color=Color.Gray, shape = RoundedCornerShape(10.dp))
@Composable
fun OrderCard(deliveryTypeLabel:String,
              orderCodeLabel:String,
              totalItemsLabel:String,
              orderPriceLabel:String,
              deliveryPriceLabel:String,
              deliveryTypeValue:String,
              taxPriceLabel:String,
              grandTotalLabel:String,
              order: Order,onClick:()->Unit={}) {
    //val generalText = Preferences.LanguageSettings().getGeneralTextLanguage()
    val items = order.items
    var itemsCount = 0
    if (items != null) {
        itemsCount = items.size
    }
    Column(modifier= modifier.clickable {
        onClick.invoke()
    }) {
        OrderCardText(orderCodeLabel, order.order_code)
        OrderCardText(totalItemsLabel,"$itemsCount")
        OrderCardText(orderPriceLabel,"$ ${order.total_value}")

        if (order.delivery_type.equals("delivery")) {
            OrderCardText(deliveryTypeLabel,deliveryTypeValue)
            OrderCardText(deliveryPriceLabel,"$ ${order.service_value?:0}")
        }
        else{
            OrderCardText(deliveryTypeLabel, deliveryTypeValue)
        }
        OrderCardText(taxPriceLabel,"$ ${order.tax_value?:0}")
        OrderCardText(grandTotalLabel,"$ ${order.total_value}")
    }
}

@Composable
fun OrderCardText(title: String, value: String) {
    Row(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = value)
    }
}