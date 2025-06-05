package br.net.ligfibra.vendedorcadastrocliente.ui.widgets.vendacard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
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
fun VendaCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White)
            .padding(vertical = 22.dp, horizontal = 26.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Carla Souza",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
            )
            StatusBadge("Ativo")
        }

        Column {
            InfoLabel(
                icon = Icons.Default.Phone,
                iconColor = MaterialTheme.colorScheme.primary,
                text = "(11) 98765-4321"
            )
            InfoLabel(
                icon = Icons.Default.LocationOn,
                iconColor = MaterialTheme.colorScheme.secondary,
                text = "Rua das Flores, 456"
            )
        }
    }
}