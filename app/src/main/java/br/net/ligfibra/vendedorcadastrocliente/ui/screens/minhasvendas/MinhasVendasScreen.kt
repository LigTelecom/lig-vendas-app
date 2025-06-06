package br.net.ligfibra.vendedorcadastrocliente.ui.screens.minhasvendas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.minhasvendas.widgets.VendaCard

@Composable
fun MinhasVendasScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 32.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = "Minhas Vendas",
            fontSize = 42.sp,
            color = Color.White,
            modifier = modifier.padding(top = 32.dp)
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            repeat(9) {
                VendaCard({navController.navigate("venda/${1}")})
            }
        }
    }
}

@Preview
@Composable
fun MinhaVendasScreenPreview() {
    VendedorcadastroclienteTheme {
        MinhasVendasScreen(
            modifier = TODO(),
            navController = TODO()
        )
    }
}