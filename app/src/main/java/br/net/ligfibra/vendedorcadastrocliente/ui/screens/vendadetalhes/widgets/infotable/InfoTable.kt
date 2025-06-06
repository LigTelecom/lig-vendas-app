package br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes.widgets.infotable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.OutlineContainer

class IconLabel(val vector: ImageVector, val color: Color)
class Label(val icon: IconLabel, val text: String, val description: String = "")

@Composable
fun InfoTable(infos: List<Label>) {
    OutlineContainer {
        infos.forEach { info ->
            Row(Modifier.padding(vertical = 4.dp)) {
                Icon(
                    imageVector = info.icon.vector,
                    contentDescription = "Icon",
                    tint = info.icon.color,
                    modifier = Modifier.size(22.dp)
                )
                Column {
                    Text(
                        text = info.text,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    if (info.description.isNotEmpty()) {
                        Text(
                            fontSize = 10.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Light,
                            text = info.description,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    }
}

@Preview
@Composable
fun InfoTablePreview() {
    val listLabel = mutableListOf<Label>(
        Label(
            icon = IconLabel(
                vector = Icons.Default.Person,
                color = MaterialTheme.colorScheme.primary
            ),
            text = "123.123.123-00"
        )
    )
    VendedorcadastroclienteTheme {
        InfoTable(listLabel)
    }
}

