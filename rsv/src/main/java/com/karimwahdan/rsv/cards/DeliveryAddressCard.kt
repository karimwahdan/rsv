package com.karimwahdan.rsv.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import com.karimwahdan.rsv.comma
import com.karimwahdan.rsv.doubleDots
import com.karimwahdan.rsv.space
import com.karimwahdan.rsm.customer.DeliveryAddress

@Composable
fun DeliveryAddressCard(borderColor:Color, selectedColor:Color,
                        normalColor:Color,
                        addressNameFontColor:Color,
                        addressCardTypeBackgroundColor:Color,
                        addressCardTypeFontColor:Color,
                        addressCardIsDefaultBackgroundColor:Color,
                        addressCardIsDefaultFontColor:Color,
                        addressCardTelFontColor:Color,
                        addressCardDetailsFontColor:Color,
                        address: DeliveryAddress,
                        detailsLabel:String,
                        isDefaultLabel:String,
                        streetLabel:String,
                        landmarkLabel:String,
                        areaLabel:String,
                        cityLabel:String,
                        countryLabel:String,
                        mobileLabel:String,
                        selected: Boolean,
                        onSelected: () -> Unit) {

    val background = if (selected) {
        selectedColor
    } else {
        normalColor
    }
    Column(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = background,
                shape = RoundedCornerShape(10.dp)
            )
            .border
                (
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(checked = selected,
                onCheckedChange = { onSelected() })
            Text(
                text = address.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color= addressNameFontColor
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .padding(horizontal = 10.dp)
                    .background(
                        color = addressCardTypeBackgroundColor,
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = addressCardTypeFontColor,
                    text = address.address_type
                )
            }

            if (address.default_address) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .padding(horizontal = 10.dp)
                        .background(
                            color = addressCardIsDefaultBackgroundColor,
                            shape = RoundedCornerShape(5.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        color = addressCardIsDefaultFontColor,
                        text = isDefaultLabel
                    )
                }
            }

            Icon(
                modifier =
                Modifier.padding(horizontal = 10.dp),
                imageVector = Icons.Default.Edit,
                contentDescription = null
            )
            Icon(
                modifier =
                Modifier.padding(horizontal = 10.dp),
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )
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

                withStyle(SpanStyle()) { append(address.state_name) }
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(comma)
                    append(countryLabel)
                    append(doubleDots)
                }
                withStyle(SpanStyle()) { append(address.country_name) }

            },
            color=addressCardDetailsFontColor
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
            },
            color=addressCardTelFontColor
        )
    }
}
