package com.iliun.stockcalccomp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.iliun.stockcalccomp.module.*
import com.iliun.stockcalccomp.ui.theme.StockCalcCompTheme
import com.iliun.stockcalccomp.ui.theme.color

class MainActivity : ComponentActivity() {
    
    val mInterstitialAd: InterstitialAd? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

            val adRequest = AdRequest.Builder().build()

//            InterstitialAd.load(this, "",adRequest,object :InterstitialAdLoadCallback() {
//            })

            val viewModel by viewModels<MyViewModel>()
            this.window.statusBarColor = ContextCompat.getColor(this,R.color.status)

            StockCalcCompTheme{
                Surface(
                    Modifier.padding(top = 5.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        )   {
                        TextBox(text = "매입차수")
                        FirstCard(viewModel = viewModel)

                        TextBox(text = "매입총액 평균단가")
                        SecondCard(viewModel = viewModel)

                        TextBox(text = "수익률")
                        ThirdCard(viewModel = viewModel)

                        Box(
                            modifier = Modifier.fillMaxSize()
                                .padding(10.dp),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            AndroidView(factory = {
                                AdView(it).apply {
                                    adSize = AdSize.FULL_BANNER
                                    adUnitId = "ca-app-pub-9826145572344148/5587107639"
                                    loadAd(AdRequest.Builder().build())
                                }
                            })
                        }

                        }
                    }
                }
            }
        }

    }



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FirstCard(viewModel: MyViewModel) {
    val focusToOneNum = remember { FocusRequester() }
    val focusToTwoBuy = remember { FocusRequester() }
    val focusToTwoNum = remember { FocusRequester() }
    val focusToThreeBuy = remember { FocusRequester() }
    val focusToThreeNum = remember { FocusRequester() }
    val focusToFourBuy = remember { FocusRequester() }
    val focusToFourNum = remember { FocusRequester() }
    val focusToFiveBuy = remember { FocusRequester() }
    val focusToFiveNum = remember { FocusRequester() }
//    val focusCurrentPrice = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var isTextFieldFocused = false

    val focusManager = LocalFocusManager.current

    Surface(

    ) {
        Column(
            Modifier.padding(4.dp)
        ) {
            //line1
            Row(
                Modifier.padding(vertical = 4.dp)
            ) {
                TextfieldUse(value = viewModel.lineOneBuy, onValueChange = {
                    viewModel.lineOneBuy = it
                    if (viewModel.lineOneBuy.isEmpty() || viewModel.lineOneNum.isEmpty()) {
                        viewModel.lineOneCalc = 0f
                    } else {
                        viewModel.lineOneCalc = calculated(viewModel.lineOneBuy, viewModel.lineOneNum)
                    }
                }, modifier = Modifier.weight(9f), placeholder = { Text(text = "단가" )},
                        keyboardActions = KeyboardActions(onNext = {focusToOneNum.requestFocus() } )
                    )
                Spacer(modifier = Modifier.weight(0.5f))

                TextfieldUse(value = viewModel.lineOneNum, onValueChange = {
                    viewModel.lineOneNum = it;
                    viewModel.lineOneNumFloat = try { it.toFloat() } catch (ex:Exception) {0f}
                    if (viewModel.lineOneBuy.isEmpty() || viewModel.lineOneNum.isEmpty()) {
                        viewModel.lineOneCalc = 0f
                    } else {
                        viewModel.lineOneCalc = calculated(viewModel.lineOneBuy, viewModel.lineOneNum)
                    }
                }, modifier = Modifier
                    .weight(9f)
                    .focusRequester(focusToOneNum), placeholder = { Text(text = "수량") },
                keyboardActions = KeyboardActions(onNext = {focusToTwoBuy.requestFocus()})
                    )
                Spacer(modifier = Modifier.weight(0.5f))

                TextfieldRead(value =
                if ( viewModel.lineOneBuy.isEmpty() || viewModel.lineOneNum.isEmpty() ) {""
                } else {calculatedStr(viewModel.lineOneBuy,viewModel.lineOneNum)},
                    onValueChange = {  },
                    modifier = Modifier.weight(14f)
                )
            }

            //line2
            Row(
                Modifier.padding(vertical = 4.dp)
            ) {
                TextfieldUse(value = viewModel.lineTwoBuy, onValueChange = {
                    viewModel.lineTwoBuy = it
                    if (viewModel.lineTwoBuy.isEmpty() || viewModel.lineTwoNum.isEmpty()) {
                        viewModel.lineTwoCalc = 0f
                    } else {
                        viewModel.lineTwoCalc = calculated(viewModel.lineTwoBuy, viewModel.lineTwoNum)
                    }
                }, modifier = Modifier
                    .weight(9f)
                    .focusRequester(focusToTwoBuy), placeholder = { Text(text = "단가") },
                    keyboardActions = KeyboardActions(onNext = {focusToTwoNum.requestFocus()})
                    )
                Spacer(modifier = Modifier.weight(0.5f))

                TextfieldUse(value = viewModel.lineTwoNum, onValueChange = {
                    viewModel.lineTwoNum = it
                    viewModel.lineTwoNumFloat = if (viewModel.lineTwoNum.isNotEmpty() ) {it.toFloat()} else 0f
                    if (viewModel.lineTwoBuy.isEmpty() || viewModel.lineTwoNum.isEmpty()) {
                        viewModel.lineTwoCalc = 0f
                    } else {
                        viewModel.lineTwoCalc = calculated(viewModel.lineTwoBuy, viewModel.lineTwoNum)
                    }
                }, modifier = Modifier
                    .weight(9f)
                    .focusRequester(focusToTwoNum), placeholder = { Text(text = "수량") },
                    keyboardActions = KeyboardActions(onNext = {focusToThreeBuy.requestFocus()})
                    )
                Spacer(modifier = Modifier.weight(0.5f))

                TextfieldRead(value =
                if ( viewModel.lineTwoBuy.isEmpty() || viewModel.lineTwoNum.isEmpty() ) {""
                }else {calculatedStr(viewModel.lineTwoBuy,viewModel.lineTwoNum)},
                onValueChange = {}, modifier = Modifier.weight(14f)
                )
            }

            //line3
            Row(
                Modifier.padding(vertical = 4.dp)
            ) {
                TextfieldUse(value = viewModel.lineThreeBuy, onValueChange = {
                    viewModel.lineThreeBuy = it
                    if (viewModel.lineThreeBuy.isEmpty() || viewModel.lineThreeNum.isEmpty()) {
                        viewModel.lineThreeCalc = 0f
                    } else {
                        viewModel.lineThreeCalc =
                            calculated(viewModel.lineThreeBuy, viewModel.lineThreeNum)
                    }
                }, modifier = Modifier
                    .weight(9f)
                    .focusRequester(focusToThreeBuy), placeholder = { Text(text = "단가") },
                    keyboardActions = KeyboardActions(onNext = {focusToThreeNum.requestFocus()})
                    )
                Spacer(modifier = Modifier.weight(0.5f))

                TextfieldUse(value = viewModel.lineThreeNum, onValueChange = {
                    viewModel.lineThreeNum = it;
                    viewModel.lineThreeNumFloat = if (viewModel.lineThreeNum.isNotEmpty() ) {it.toFloat()} else 0f
                    if (viewModel.lineThreeBuy.isEmpty() || viewModel.lineThreeNum.isEmpty()) {
                        viewModel.lineThreeCalc = 0f
                    } else {
                        viewModel.lineThreeCalc = calculated(viewModel.lineThreeBuy, viewModel.lineThreeNum)
                    } },
                    modifier = Modifier
                        .weight(9f)
                        .focusRequester(focusToThreeNum), placeholder = { Text(text = "수량") },
                    keyboardActions = KeyboardActions(onNext = {focusToFourBuy.requestFocus()})
                    )
                Spacer(modifier = Modifier.weight(0.5f))

                TextfieldRead(value =
                if ( viewModel.lineThreeBuy.isEmpty() || viewModel.lineThreeNum.isEmpty() ) {""
                }else {calculatedStr(viewModel.lineThreeBuy,viewModel.lineThreeNum)},
                onValueChange = {}, modifier = Modifier.weight(14f)
                )
            }

            //line4
            Row(
                Modifier.padding(vertical = 4.dp)
            ) {
                TextfieldUse(value = viewModel.lineFourBuy, onValueChange = {
                    viewModel.lineFourBuy = it
                    if (viewModel.lineFourBuy.isEmpty() || viewModel.lineFourNum.isEmpty()) {
                        viewModel.lineFourCalc = 0f
                    } else {
                        viewModel.lineFourCalc =
                            calculated(viewModel.lineFourBuy, viewModel.lineFourNum)
                    }
                }, modifier = Modifier
                    .weight(9f)
                    .focusRequester(focusToFourBuy), placeholder = { Text(text = "단가") },
                    keyboardActions = KeyboardActions(onNext = {focusToFourNum.requestFocus()})
                    )
                Spacer(modifier = Modifier.weight(0.5f))

                TextfieldUse(value = viewModel.lineFourNum, onValueChange = {
                    viewModel.lineFourNum = it;
                    viewModel.lineFourNumFloat = if (viewModel.lineFourNum.isNotEmpty() ) {it.toFloat()} else 0f
                    if (viewModel.lineFourBuy.isEmpty() || viewModel.lineFourNum.isEmpty()) {
                        viewModel.lineFourCalc = 0f
                    } else {
                        viewModel.lineFourCalc = calculated(viewModel.lineFourBuy, viewModel.lineTwoNum) }
                }, modifier = Modifier
                    .weight(9f)
                    .focusRequester(focusToFourNum), placeholder = { Text(text = "수량") },
                    keyboardActions = KeyboardActions(onNext = {focusToFiveBuy.requestFocus()})
                    )
                Spacer(modifier = Modifier.weight(0.5f))

                TextfieldRead(value =
                if ( viewModel.lineFourBuy.isEmpty() || viewModel.lineFourNum.isEmpty() ) {""
                }else {calculatedStr(viewModel.lineFourBuy,viewModel.lineFourNum)},
                onValueChange = {}, modifier = Modifier.weight(14f)
                )
            }

            
            //line5
            Row(
                Modifier.padding(vertical = 4.dp)
            ) {
                TextfieldUse(value = viewModel.lineFiveBuy, onValueChange = {
                    viewModel.lineFiveBuy = it
                    if (viewModel.lineFiveBuy.isEmpty() || viewModel.lineFiveNum.isEmpty()) {
                        viewModel.lineFiveCalc = 0f
                    } else {
                        viewModel.lineFiveCalc =
                            calculated(viewModel.lineFiveBuy, viewModel.lineFiveNum)
                    }
                }, modifier = Modifier
                    .weight(9f)
                    .focusRequester(focusToFiveBuy), placeholder = { Text(text = "단가") },
                    keyboardActions = KeyboardActions(onNext = {focusToFiveNum.requestFocus()})
                    )
                Spacer(modifier = Modifier.weight(0.5f))

                TextfieldUse(value = viewModel.lineFiveNum, onValueChange = {
                    viewModel.lineFiveNum = it;
                    viewModel.lineFiveNumFloat = if (viewModel.lineFiveNum.isNotEmpty() ) {it.toFloat()} else 0f
                    if (viewModel.lineFiveBuy.isEmpty() || viewModel.lineFiveNum.isEmpty()) {
                        viewModel.lineFiveCalc = 0f
                    } else {
                        viewModel.lineFiveCalc =
                            calculated(viewModel.lineFiveBuy, viewModel.lineFiveNum)
                    }
                }, modifier = Modifier
                    .weight(9f)
                    .focusRequester(focusToFiveNum), placeholder = { Text(text = "수량") },
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(
                        FocusDirection.Down)})
                    )
                Spacer(modifier = Modifier.weight(0.5f))

                TextfieldRead(value = if ( viewModel.lineFiveBuy.isEmpty() || viewModel.lineFiveNum.isEmpty() ) {""
                }else {calculatedStr(viewModel.lineFiveBuy,viewModel.lineFiveNum)},
                 onValueChange = {}, modifier = Modifier.weight(14f)
                )
            }
        }
    }


}



@Composable
fun SecondCard(viewModel: MyViewModel) {
        Row(
            Modifier.padding(top = 6.dp, bottom = 6.dp, start = 4.dp, end = 4.dp)
        ) {
                TextfieldRead(value = totalCalc(viewModel = viewModel,viewModel.lineOneCalc,viewModel.lineTwoCalc,viewModel.lineThreeCalc,viewModel.lineFourCalc,viewModel.lineFiveCalc),
                    onValueChange = {}
                    , modifier = Modifier)
            Spacer(modifier = Modifier.width(6.dp))
            OutlinedTextField(
                value = avgPrice(viewModel = viewModel),
                onValueChange = {},
                placeholder = { Text(text = "평균단가")},
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            readOnly = true,
            )
        }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ThirdCard(viewModel: MyViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusCurrentPrice = remember { FocusRequester() }
    Row(
        Modifier.padding(top = 6.dp, bottom = 6.dp, start = 4.dp, end = 4.dp)
    ) {
        OutlinedTextField(value = viewModel.presentPriceStr,
            onValueChange = {viewModel.presentPriceStr = it; viewModel.presentPriceFloat = if (viewModel.presentPriceStr.isNotEmpty() ) {it.toFloat()} else {0f} },
            placeholder = { Text(text = "현재가격 입력")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,  imeAction = ImeAction.Done),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            singleLine = true,
            modifier = Modifier.focusRequester(focusCurrentPrice),
            keyboardActions = KeyboardActions(onDone ={keyboardController?.hide()} )
        )
        Spacer( modifier = Modifier.width(6.dp) )
        OutlinedTextField(value = returnPercent(viewModel = viewModel)+"%",
            onValueChange = { },
            readOnly = true,
            placeholder = { Text(text = "%", textAlign = TextAlign.End)},
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
        )
    }
}

@Composable
fun TextBox(text:String) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = color)
                .padding(vertical = 14.dp, horizontal = 12.dp),
        )
    }
}




//@Preview(showBackground = true)
//@Composable
//fun Preview(viewModel: MyViewModel) {
//    TextBox(text = "첫번째 박스")
//}