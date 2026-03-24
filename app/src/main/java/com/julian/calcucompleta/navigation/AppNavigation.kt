package com.julian.calcucompleta.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.julian.calcucompleta.calculatorModule.view.CalculatorScreen
import com.julian.calcucompleta.recordModule.view.RecordScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "calculator"
    ) {
        // Estilo exacto al ejemplo que mandaste
        composable("calculator") {
            CalculatorScreen(navController)
        }

        composable("history") {
            RecordScreen(navController)
        }
    }
}
