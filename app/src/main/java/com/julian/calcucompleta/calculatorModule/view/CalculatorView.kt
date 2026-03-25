package com.julian.calcucompleta.calculatorModule.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.julian.calcucompleta.calculatorModule.viewmodel.CalculatorViewModel
import com.julian.calcucompleta.ui.theme.CalcuCompletaTheme

@Composable
fun CalculatorButton(
    backgroundColor: Color,
    text: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick(text) },
        modifier = modifier,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ButtonGrid(
    modifier: Modifier = Modifier,
    onButtonClick: (String) -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(
            8.dp,
            alignment = Alignment.Bottom
        )
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CalculatorButton(MaterialTheme.colorScheme.tertiary, "⌫", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.tertiary, "AC", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.tertiary, "%", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.primary, "÷", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CalculatorButton(MaterialTheme.colorScheme.secondary, "7", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.secondary, "8", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.secondary, "9", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.primary, "x", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CalculatorButton(MaterialTheme.colorScheme.secondary, "4", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.secondary, "5", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.secondary, "6", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.primary, "-", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CalculatorButton(MaterialTheme.colorScheme.secondary, "1", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.secondary, "2", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.secondary, "3", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.primary, "+", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CalculatorButton(MaterialTheme.colorScheme.secondary, "+/-", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.secondary, "0", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.secondary, ",", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
            CalculatorButton(MaterialTheme.colorScheme.primary, "=", onButtonClick, Modifier.weight(1f).aspectRatio(1f))
        }
    }
}

@Composable
fun CalculatorScreen(
    navController: NavController,
    viewModel: CalculatorViewModel = viewModel()
) {
    val operation = viewModel.operation

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            IconButton(onClick = { navController.navigate("history") }) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = "Historial",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxWidth().weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {

                val preview = viewModel.previewResult
                if (preview != null) {
                    Text(
                        text = preview,
                        fontSize = 26.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                Text(
                    text = operation.joinToString(" ").replace(".", ","),
                    fontSize = 40.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
        ButtonGrid(
            modifier = Modifier.fillMaxWidth()
        ) { value ->
            viewModel.onButtonClick(value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalcuCompletaTheme {
        val navController = rememberNavController()
        CalculatorScreen(navController = navController)
    }
}
