package com.julian.calcucompleta.calculatorModule.model

class CalculatorModel {
    /**
     * evaluateExpression
     *
     * Motor de cálculo que resuelve la expresión matemática contenida en la lista de tokens.
     */
    fun evaluateExpression(tokens: List<String>): Double? {
        if (tokens.isEmpty()) return null
        // Se crea una copia mutable para trabajarla
        val list = tokens.toMutableList()

        return try {
            // 1. NIVEL 1: Multiplicación, División y Módulo (De izquierda a derecha)
            var i = 1
            while (i < list.size - 1) {
                val op = list[i]
                if (op == "x" || op == "÷" || op == "%") {
                    val left = list[i - 1].toDoubleOrNull() ?: return null
                    val right = list[i + 1].toDoubleOrNull() ?: return null

                    val result = when (op) {
                        "x" -> left * right
                        "÷" -> if (right == 0.0) throw ArithmeticException() else left / right
                        "%" -> left % right
                        else -> 0.0
                    }

                    list[i - 1] = result.toString()
                    list.removeAt(i + 1)
                    list.removeAt(i)
                    i = 0 // Evalua la nueva lista desde el inicio
                    continue
                }
                i++
            }

            // 2. NIVEL 2: Suma y Resta (De izquierda a derecha)
            i = 1
            while (i < list.size - 1) {
                val op = list[i]
                if (op == "+" || op == "-") {
                    val left = list[i - 1].toDoubleOrNull() ?: return null
                    val right = list[i + 1].toDoubleOrNull() ?: return null

                    val result = if (op == "+") left + right else left - right

                    list[i - 1] = result.toString()
                    list.removeAt(i + 1)
                    list.removeAt(i)
                    i = 0
                    continue
                }
                i++
            }

            list.firstOrNull()?.toDouble()
        } catch (e: Exception) {
            null
        }
    }
}
