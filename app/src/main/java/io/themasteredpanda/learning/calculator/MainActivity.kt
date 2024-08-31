package io.themasteredpanda.learning.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.squareup.duktape.Duktape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        }
    }
}

fun checkEquation(equation: String): String? {
    val doubleArithmeticCheck = Regex("[+\\-*/%]{2,}")
    val unfollowedArithmeticFunction = Regex("[+*/%](?!\\d)|-(?!\\d)")


    if (doubleArithmeticCheck.containsMatchIn(equation)) {
        return "Doubled arithmetic functions found."
    }

    if (unfollowedArithmeticFunction.containsMatchIn(equation)) {
        return "Unfollowed arithmetic function."
    }

    return null
}

fun sum(equation: String): Any? {
    val duktape = Duktape.create()
    val result = duktape.evaluate(equation)
    return result
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CalculatorApp() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            var numberCounter = 1
            for (i in 1..5) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    for (j in 1..4) {
                        if (j != 4 && i <= 3) {
                            NumberButton(mods = Modifier.weight(1f), number = numberCounter)
                            numberCounter++
                        } else {
                            when (i) {
                                1 -> {
                                    OperatorButton(mods = Modifier.weight(1f), op = "+")
                                }

                                2 -> {
                                    OperatorButton(mods = Modifier.weight(1f), op = "-")
                                }

                                3 -> {
                                    OperatorButton(mods = Modifier.weight(1f), op = "/")
                                }
                            }
                        }
                    }

                    if (i == 5) {
                        NumberButton(mods = Modifier.weight(.2f), number = 0)
                        OperatorButton(mods = Modifier.weight(.2f), op = "CE")
                        OperatorButton(mods = Modifier.weight(.6f), op = "=")
                    }
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CalculatorPreview() {
    CalculatorApp()
}