package com.julian.calcucompleta.recordModule.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.julian.calcucompleta.recordModule.viewmodel.RecordViewModel

@Composable
fun RecordScreen(
    navController: NavController,
    viewModel: RecordViewModel = viewModel()
) {
    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(16.dp)) {
                Text("Volver")
            }
            LazyColumn {
                items(viewModel.history) { item ->
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = item.expression, fontSize = 16.sp, color = Color.White)
                        Text(text = "= ${item.result}", fontSize = 22.sp, color = MaterialTheme.colorScheme.primary)
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
