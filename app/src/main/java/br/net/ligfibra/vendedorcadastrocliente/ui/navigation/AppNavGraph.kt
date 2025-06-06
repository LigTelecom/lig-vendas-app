package br.net.ligfibra.vendedorcadastrocliente.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.MainScreen
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.auth.AuthScreen
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes.VendaDetalhesScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "auth") {
        composable("auth") {
            AuthScreen(onEnterClick = { navController.navigate("main") })
        }
        composable("main") {
            MainScreen(navController = navController)
        }
        composable("venda/{vendaId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("vendaId").toString()
            VendaDetalhesScreen(vendaId = id)
        }
    }
}