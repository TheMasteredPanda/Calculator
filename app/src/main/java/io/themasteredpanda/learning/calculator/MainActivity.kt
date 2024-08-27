package io.themasteredpanda.learning.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.themasteredpanda.learning.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        var buttonNumber = 1

                        for (i in 1..3) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                for (j in 1..3) {
                                    ArithmeticButton(
                                        modifier = Modifier.weight(1f),
                                        number = buttonNumber
                                    )
                                    buttonNumber++
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ArithmeticButton(modifier: Modifier, number: Int) {
    Button(
        onClick = { println("click") },
        modifier = modifier,
        shape = RoundedCornerShape(0.dp)
    ) { Text("$number") }
}

@Composable
fun FunctionButton(modifier: Modifier, bFunction: String) {
    Button(
        onClick = { println("Click") },
        modifier = modifier,
        shape = RoundedCornerShape(0.dp)
    ) { Text(bFunction) }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                .padding(5.dp)


            for (i in 1..3) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    for (j in 1..3) {
                        ArithmeticButton(
                            modifier = buttonModifiers, number = buttonNumber
                        )

                        buttonNumber++
                    }

                    when (i) {
                        1 -> FunctionButton(modifier = buttonModifiers, bFunction = "+")
                        2 -> FunctionButton(modifier = buttonModifiers, bFunction = "-")
                        3 -> FunctionButton(modifier = buttonModifiers, bFunction = "/")
                    }
                }
            }

            Row (modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                ArithmeticButton(modifier = buttonModifiers, number = 0)
                FunctionButton(modifier = buttonModifiers, bFunction = "CE")
                FunctionButton(modifier = buttonModifiers, bFunction = "C")
                FunctionButton(modifier = buttonModifiers, bFunction = "=")
            }
        }
    }
}