package com.iliun.stockcalccomp.module

import com.iliun.stockcalccomp.MyViewModel
import java.text.DecimalFormat

fun makeCommaNumberInt(input:Int):String{
    val fomatter = DecimalFormat("###,###")
    return fomatter.format(input)
}

fun makeCommaNumberFloat(input:Float):String{
    val fomatter = DecimalFormat("###,###.##")
    return fomatter.format(input)
}

fun makeDigitNumberString(input:String):String {
    val fomatter = DecimalFormat("###,###.##")
    return fomatter.format(input)
}

fun calculated(valueOne:String, valueTwo:String): Float {
    val calculation:Float
    val one = valueOne.toFloat()
    val two = valueTwo.toFloat()
    calculation = one * two
    return calculation
}

fun calculatedStr(valueOne:String, valueTwo:String): String {
    val calculation:Float
    val one = valueOne.toFloat()
    val two = valueTwo.toFloat()
    calculation = one * two
    val fomatter = DecimalFormat("###,###.##")
    return fomatter.format(calculation)
}

fun totalCalc(viewModel: MyViewModel,floatOne:Float, floatTwo:Float, floatThree:Float, floatFour:Float, floatFive:Float):String {
    val total = floatOne + floatTwo + floatThree + floatFour + floatFive
    val fomatter = DecimalFormat("###,###.##")
    return fomatter.format(total)
//    return total.toString()
}

fun totalBuyCalc(
    floatOne: Float,
    floatTwo: Float,
    floatThree: Float,
    floatFour: Float,
    floatFive: Float
): Float {
    return floatOne + floatTwo + floatThree + floatFour + floatFive
}


fun avgPrice(viewModel: MyViewModel ):String {
    val total = (
            (viewModel.lineOneCalc+ viewModel.lineTwoCalc+viewModel.lineThreeCalc+viewModel.lineFourCalc+viewModel.lineFiveCalc) /
                    (
                            viewModel.lineOneNumFloat +
                                    viewModel.lineTwoNumFloat +
                                    viewModel.lineThreeNumFloat +
                                    viewModel.lineFourNumFloat +
                                    viewModel.lineFiveNumFloat
                            )
            )
    viewModel.avgPriceFloat = total
    val fomatter = DecimalFormat("###,###.##")
    return fomatter.format(total)
}

fun returnPercent(viewModel: MyViewModel): String {
   val total =  ((viewModel.presentPriceFloat / viewModel.avgPriceFloat) * 100) - 100
    val fomatter = DecimalFormat("###,###.##")
    return fomatter.format(total)
}