package com.julian.calcucompleta.recordModule.model

/**
 * Representa una entrada individual en el historial.
 */
data class RecordItem(
    val expression: String, // La operación completa (ej: "10 + 5")
    val result: String      // El resultado obtenido (ej: "15")
)