package com.iliun.stockcalccomp

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TextfieldUse(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    keyboardActions: KeyboardActions,
    placeholder: @Composable (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange =  onValueChange,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
        placeholder = placeholder,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        keyboardActions = keyboardActions
    )

}

@Composable
fun TextfieldRead(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange =  onValueChange,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        readOnly = true,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
    )

}