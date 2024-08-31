package io.themasteredpanda.learning.calculator

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
* File for handling button logic.
*/



@Composable
fun NumberButton(mods: Modifier, number: Int) {
    Button(modifier = mods, onClick = {

    }) {
        Text(text = number.toString())
    }
}


@Composable
fun OperatorButton(mods: Modifier, op: String) {
    Button(modifier = mods, onClick = { /*TODO*/ }) {
        Text(text = op.uppercase())
    }
}

//Handling functions that are not operators. Such as CE. In fact, just CE.
@Composable
fun FunctionButton(mods: Modifier, fFunc: String) {
    Button(modifier = mods, onClick = { /*TODO*/ }) {
        Text(text = fFunc.uppercase())
    }
}