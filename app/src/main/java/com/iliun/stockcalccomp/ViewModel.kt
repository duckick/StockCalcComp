package com.iliun.stockcalccomp

import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel


class MyViewModel: ViewModel() {
    var lineOneBuy by  mutableStateOf("")
    var lineOneNum by   mutableStateOf("")
    var lineOneNumFloat by mutableStateOf(0f)
    var lineOnetotal by  mutableStateOf(0f)
    var lineOneCalc by  mutableStateOf(0f)
    var lineOneResultStr by mutableStateOf("")


    var lineTwoBuy by  mutableStateOf("")
    var lineTwoNum by  mutableStateOf("")
    var lineTwoNumFloat by mutableStateOf(0f)
    var lineTwototal by mutableStateOf(0f)
    var lineTwoCalc by mutableStateOf(0f)

    var lineThreeBuy by  mutableStateOf("")
    var lineThreeNum by  mutableStateOf("")
    var lineThreeNumFloat by mutableStateOf(0f)
    var lineThreetotal by  mutableStateOf(0f)
    var lineThreeCalc by  mutableStateOf(0f)

    var lineFourBuy by  mutableStateOf("")
    var lineFourNum by  mutableStateOf("")
    var lineFourNumFloat by mutableStateOf(0f)
    var lineFourtotal by  mutableStateOf(0f)
    var lineFourCalc by  mutableStateOf(0f)

    var lineFiveBuy by  mutableStateOf("")
    var lineFiveNum by  mutableStateOf("")
    var lineFiveNumFloat by mutableStateOf(0f)
    var lineFivetotal by mutableStateOf(0f)
    var lineFiveCalc by mutableStateOf(0f)

    var avgPriceFloat by mutableStateOf(0f)
    var presentPriceFloat by mutableStateOf(0f)
    var presentPriceStr by mutableStateOf("")
    var totalBuyFloat by mutableStateOf(0f)

}