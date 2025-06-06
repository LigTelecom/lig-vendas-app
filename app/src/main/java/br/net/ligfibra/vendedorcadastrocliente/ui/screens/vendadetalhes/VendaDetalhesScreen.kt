package br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun VendaDetalhesScreen(vendaId: String) {
    Column {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Detalhes Venda Page $vendaId",
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
    }
}