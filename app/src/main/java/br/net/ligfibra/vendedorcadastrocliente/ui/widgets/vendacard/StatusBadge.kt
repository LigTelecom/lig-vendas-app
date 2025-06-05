package br.net.ligfibra.vendedorcadastrocliente.ui.widgets.vendacard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusBadge(status: String) {
    Box(
        modifier = Modifier
            .width(86.dp)
            .height(32.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(MaterialTheme.colorScheme.secondary),
    ) {
        Text(text = "Ativo",
            color = Color.White,
            fontSize = 22.sp,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}