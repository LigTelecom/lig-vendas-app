package br.net.ligfibra.vendedorcadastrocliente.ui.screens.minhasvendas.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme

@Composable
fun StatusBadge(status: String) {
    Box(
        modifier = Modifier
            .width(86.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = "Ativo",
            color = Color.White,
            fontSize = 22.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 2.dp)
        )
    }
}

@Preview
@Composable
private fun StatusBadgePreview() {
    VendedorcadastroclienteTheme {
        StatusBadge("Ativo")
    }
}