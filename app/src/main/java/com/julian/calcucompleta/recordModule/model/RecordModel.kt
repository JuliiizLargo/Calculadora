package com.julian.calcucompleta.recordModule.model

import androidx.compose.runtime.mutableStateListOf

class RecordModel {
    companion object {
        val records = mutableStateListOf<RecordItem>()
    }
}
