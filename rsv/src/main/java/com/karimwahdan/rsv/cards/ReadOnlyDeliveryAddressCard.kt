package com.karimwahdan.rsv.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.karimwahdan.rsv.comma
import com.karimwahdan.rsv.doubleDots
import com.karimwahdan.rsv.space
import com.karimwahdan.rsm.customer.DeliveryAddress

@Composable
fun ReadOnlyDeliveryAddressCard(borderColor:Color,
                                notSelectedBackgroundColor:Color,
                                nameFontColor:Color,
                                typeBackgroundColor: Color,
                                typeFontColor:Color,
                                detailsLabel:String,
                                streetLabel:String,
                                landmarkLabel:String,
                                areaLabel:String, cityLabel:String,
                                countryLabel:String,
                                mobileLabel:String,
                                address: DeliveryAddress) {
    val rcs10=RoundedCornerShape(10.dp)
    val rcs5=RoundedCornerShape(5.dp)
    val bold=FontWeight.Bold
    Column(
        modifier = Modifier.padding(5.dp).clip(rcs10)
            .background(color = notSelectedBackgroundColor,shape = rcs10)
            .border(width = 1.dp,color = borderColor,shape = rcs10)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        ) {
            Text(fontWeight = bold,text = address.name,color = nameFontColor,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).weight(1f))
            Box(
                modifier = Modifier.clip(rcs5).padding(horizontal = 10.dp)
                    .background(color = typeBackgroundColor,shape = rcs5)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = typeFontColor,text = address.address_type
                )
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 5.dp),
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(detailsLabel)
                    append(doubleDots)
                    append(space)
                    append(streetLabel)
                    append(doubleDots)
                }
                withStyle(SpanStyle()) {
                    append(address.address)
                }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(comma)
                    append(landmarkLabel)
                    append(doubleDots)
                }
                withStyle(SpanStyle()) { append(address.landmark) }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(comma)
                    append(areaLabel)
                    append(doubleDots)
                }
                withStyle(SpanStyle()) { append(address.area_name) }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(comma)
                    append(cityLabel)
                    append(doubleDots)
                }
                withStyle(SpanStyle()) { append(address.city_name) }

                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(comma)
                    append(countryLabel)
                    append(doubleDots)
                }
                withStyle(SpanStyle()) { append(address.country_name) }

            }
        )
        Text(
            modifier = Modifier.padding(horizontal = 25.dp, vertical = 5.dp),
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(mobileLabel)
                    append(doubleDots)
                }
                withStyle(SpanStyle()) {
                    append(address.mobile_no)
                }
            }
        )
    }
}


@Preview
@Composable
fun ReadOnlyDeliveryAddressCardPreview() {
}