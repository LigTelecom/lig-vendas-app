package br.net.ligfibra.vendedorcadastrocliente.ui.screens.minhasvendas.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme

@Composable
fun InfoLabel(icon: ImageVector, iconColor: Color, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Icon",
            tint = iconColor,
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Preview
@Composable
fun InfoLabelPreview() {
    VendedorcadastroclienteTheme {
        InfoLabel(
            icon = Icons.Default.Phone,
            iconColor = MaterialTheme.colorScheme.primary,
            text = "(11) 98765-4321"
        )
    }
}