package com.julian.calcucompleta.recordModule.viewmodel

import androidx.lifecycle.ViewModel
import com.julian.calcucompleta.recordModule.model.RecordModel

/**
 * RecordViewModel
 *
 * Actúa como puente entre el modelo de datos (historial) y la interfaz de usuario.
 */
class RecordViewModel : ViewModel() {
    /**
     * history
     *
     * Expone la lista de registros almacenada en el modelo para ser mostrada en la vista.
     */
    val history = RecordModel.records
}
