package com.julian.calcucompleta.calculatorModule.viewmodel

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.julian.calcucompleta.calculatorModule.model.CalculatorModel
import com.julian.calcucompleta.recordModule.model.RecordItem
import com.julian.calcucompleta.recordModule.model.RecordModel

class CalculatorViewModel : ViewModel() {
    private val model = CalculatorModel()
    val operation = mutableStateListOf<String>()

    /**
     * previewResult
     *
     * Previsualización en tiempo real del resultado antes de presionar el botón de igual.
     */
    val previewResult by derivedStateOf {
        // Solo se calcula si hay al menos una operación (ej: 5 + 3)
        // y el último elemento no es un operador
        if (operation.size >= 3 && operation.last() !in getOperators()) {
            val res = model.evaluateExpression(operation.toList())
            if (res != null) {
                if (res % 1 == 0.0) res.toInt().toString().replace(".", ",")
                else res.toString().replace(".", ",")
            } else null
        } else null
    }

    private fun getOperators() = listOf("+", "-", "x", "÷", "%")

    /**
     * onButtonClick
     *
     * Gestiona los eventos de clic en los botones de la calculadora y actualiza el estado de la operación.
     */
    fun onButtonClick(value: String) {
        when (value) {
            in "0".."9" -> {
                if (operation.isEmpty() || operation.last() in getOperators()) {
                    operation.add(value)
                } else {
                    // Límite de 12 dígitos por número
                    val currentNumber = operation.last()
                    val digitCount = currentNumber.count { it.isDigit() }
                    if (digitCount < 12) {
                        operation[operation.lastIndex] += value
                    }
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
                if (operation.isNotEmpty()) {
                    if (operation.last() in getOperators()) {
                        // Si el último ya era un operador, se reemplaza
                        operation[operation.lastIndex] = value
                    } else {
                        // Si no era un operador, se añade normalmente
                        operation.add(value)
                    }
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
                    // Verifica si hay al menos un operador y la operación tiene una estructura mínima (n op n)
                    val hasOperator = operation.any { it in getOperators() }
                    val isFullOperation = operation.size >= 3

                    val expression = operation.joinToString(" ").replace(".", ",")
                    val resultValue = model.evaluateExpression(operation.toList())
                    
                    operation.clear()
                    
                    if (resultValue != null) {
                        val formatted = if (resultValue % 1 == 0.0) resultValue.toInt().toString() else resultValue.toString()
                        operation.add(formatted)
                        
                        // Guardar en el historial solo si es una operación completa
                        if (hasOperator && isFullOperation) {
                            RecordModel.records.add(
                                RecordItem(
                                    expression = expression,
                                    result = formatted.replace(".", ",")
                                )
                            )
                        }
                    } else {
                        operation.add("Error")
                    }
                }
            }
        }
    }
}
