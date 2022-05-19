package com.iliun.stockcalccomp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

//@Composable
//fun AdvertView(modifier: Modifier = Modifier) {
//    val isInEditMode = LocalInspectionMode.current
//    if (isInEditMode) {
//        Text(
//            modifier = modifier
//                .fillMaxWidth()
//                .background(Color.Red)
//                .padding(horizontal = 2.dp, vertical = 6.dp),
//            textAlign = TextAlign.Center,
//            color = Color.White,
//            text = "Advert Here",
//        )
//    } else {
//        AndroidView(
//            modifier = modifier.fillMaxWidth(),
//            factory = { context ->
//                AdView(context).apply {
//                    adSize = AdSize.BANNER
//                    adUnitId = context.getString("배너id")
//                    loadAd(AdRequest.Builder().build())
//                }
//            },
//            update = {
//                it.loadAd(AdRequest.Builder().build())
//            }
//        )
//
//    }
//}