package com.julian.calcucompleta.recordModule.viewmodel

import androidx.lifecycle.ViewModel
import com.julian.calcucompleta.recordModule.model.RecordModel

class RecordViewModel : ViewModel() {
    // Expone la lista que vive en el modelo
    val history = RecordModel.records
}