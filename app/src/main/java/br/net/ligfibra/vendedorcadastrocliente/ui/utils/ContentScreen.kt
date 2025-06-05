package br.net.ligfibra.vendedorcadastrocliente.ui.utils

sealed class ContentScreen(val route: String) {
    object Home : ContentScreen("home")
    object MinhasVendas : ContentScreen("minhasvendas")
}