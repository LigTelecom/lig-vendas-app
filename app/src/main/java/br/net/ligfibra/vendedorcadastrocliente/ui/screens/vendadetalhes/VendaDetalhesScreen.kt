package br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes.widgets.carddetalhe.CardDetalheVenda
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.AppBar


@Composable
fun VendaDetalhesScreen(vendaId: String, navController: NavController) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
    ) {
    AppBar(navController)
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardDetalheVenda()
        }
    }
}

@Preview
@Composable
private fun VendaDetalhesScreenPreview() {
    VendedorcadastroclienteTheme {
        VendaDetalhesScreen(navController = TODO(), vendaId = "1")
    }
}
