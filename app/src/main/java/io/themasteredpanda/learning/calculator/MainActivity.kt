package io.themasteredpanda.learning.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.squareup.duktape.Duktape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaluclatorApp()
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

fun sum(equation: String) {
    val duktape = Duktape.create()
    val result = duktape.evaluate(equation)
    println(result)
}

@Composable
fun CaluclatorApp() {
    var equation by remember {
        mutableStateOf("")
    }

    @Composable
    fun ArithmeticButton(modifier: Modifier, number: Int) {
        Button(
            onClick = {
                equation = equation + number.toString()
            },
            modifier = modifier,
            shape = RoundedCornerShape(0.dp)
        ) { Text("$number") }
    }

    @Composable
    fun FunctionButton(modifier: Modifier, bFunction: String) {
        Button(
            onClick = {
                if (bFunction != "=" && bFunction != "CE") {
                    equation = equation + bFunction
                } else {
                    if (bFunction == "=") {
                        println("Equals hit, checking equation")
                        val result = checkEquation(equation)
                        println("Checking equation")

                        if (result == null) {
                            println("Is null")
                            sum(equation)
                        } else {
                            println("Is not null")
                        }
                    }
                }
            },
            modifier = modifier,
            shape = RoundedCornerShape(0.dp)
        ) { Text(bFunction) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxHeight(.2f)
                .fillMaxWidth()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.align(Alignment.End),
                fontSize = 20.sp,
                text = equation
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8f)
                .align(Alignment.BottomCenter)
        ) {
            var buttonNumber = 1
            val buttonModifiers = Modifier
                .weight(1f)
                .fillMaxSize(1f)

            for (i in 1..3) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    for (j in 1..3) {
                        ArithmeticButton(
                            modifier = buttonModifiers,
                            number = buttonNumber
                        )

                        buttonNumber++
                    }

                    when (i) {
                        1 -> FunctionButton(
                            modifier = buttonModifiers,
                            bFunction = "+"
                        )

                        2 -> FunctionButton(
                            modifier = buttonModifiers,
                            bFunction = "-"
                        )

                        3 -> FunctionButton(
                            modifier = buttonModifiers,
                            bFunction = "/"
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                ArithmeticButton(
                    modifier = buttonModifiers,
                    number = 0
                )
                FunctionButton(modifier = buttonModifiers, bFunction = "CE")
                FunctionButton(modifier = buttonModifiers, bFunction = "C")
                FunctionButton(modifier = buttonModifiers, bFunction = "=")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CaluclatorApp()
}