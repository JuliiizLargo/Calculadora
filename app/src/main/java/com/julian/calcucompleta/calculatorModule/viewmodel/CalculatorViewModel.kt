package com.julian.calcucompleta.calculatorModule.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.julian.calcucompleta.calculatorModule.model.CalculatorModel
import com.julian.calcucompleta.recordModule.model.RecordItem
import com.julian.calcucompleta.recordModule.model.RecordModel

class CalculatorViewModel : ViewModel() {
    private val model = CalculatorModel()
    val operation = mutableStateListOf<String>()

    private fun getOperators() = listOf("+", "-", "x", "÷", "%")

    fun onButtonClick(value: String) {
        when (value) {
            in "0".."9" -> {
                if (operation.isEmpty() || operation.last() in getOperators()) {
                    operation.add(value)
                } else {
                    operation[operation.lastIndex] += value
                }
            }

            "," -> {
                if (operation.isEmpty() || operation.last() in getOperators()) {
                    operation.add("0.")
                } else if (!operation.last().contains(".")) {
                    operation[operation.lastIndex] += "."
                }
            }

            "+/-" -> {
                if (operation.isNotEmpty()) {
                    val last = operation.last()
                    if (last !in getOperators()) {
                        if (last.startsWith("-")) {
                            operation[operation.lastIndex] = last.drop(1)
                        } else {
                            operation[operation.lastIndex] = "-$last"
                        }
                    }
                }
            }

            in getOperators() -> {
                if (operation.isNotEmpty() && operation.last() !in getOperators()) {
                    operation.add(value)
                }
            }

            "⌫" -> {
                if (operation.isNotEmpty()) {
                    val last = operation.last()
                    if (last.length > 1) {
                        operation[operation.lastIndex] = last.dropLast(1)
                    } else {
                        operation.removeAt(operation.lastIndex)
                    }
                }
            }

            "AC" -> {
                operation.clear()
            }

            "=" -> {
                if (operation.isNotEmpty()) {
                    val expression = operation.joinToString(" ").replace(".", ",")
                    val resultValue = model.evaluateExpression(operation.toList())
                    
                    operation.clear()
                    
                    if (resultValue != null) {
                        val formatted = if (resultValue % 1 == 0.0) resultValue.toInt().toString() else resultValue.toString()
                        operation.add(formatted)
                        
                        // Guardar en el historial
                        RecordModel.records.add(
                            RecordItem(
                                expression = expression,
                                result = formatted.replace(".", ",")
                            )
                        )
                    } else {
                        operation.add("Error")
                    }
                }
            }
        }
    }
}
