package br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes.widgets.carddetalhe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.minhasvendas.widgets.StatusBadge
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes.widgets.infotable.IconLabel
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes.widgets.infotable.InfoTable
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes.widgets.infotable.Label
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.md_theme_success
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.OutlineContainer

@Composable
fun CardDetalheVenda() {
    val listLabel = mutableListOf<Label>(
        Label(
            icon = IconLabel(
                vector = Icons.Default.Person,
                color = MaterialTheme.colorScheme.primary
            ),
            text = "123.123.123-00"
        ),
        Label(
            icon = IconLabel(
                vector = Icons.Default.Phone,
                color = md_theme_success
            ),
            text = "(11) 9999-9999"
        ),
        Label(
            icon = IconLabel(
                vector = Icons.Default.LocationOn,
                color = MaterialTheme.colorScheme.secondary
            ),
            text = "Rua das Flores, 456, Centro",
            description = "Perto da casa amarela"
        ),
    )
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(28.dp))
            .background(Color.White)
    ) {

        Column(modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 32.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Carla Souza",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 32.sp,
                )
                StatusBadge("Ativo")
            }
            Spacer(Modifier.height(12.dp))
            InfoTable(infos = listLabel)
            Spacer(Modifier.height(12.dp))
            OutlineContainer {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal,
                        text = "Plano"
                    )
                    Text(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal,
                        text = "ELITE 500mb"
                    )
                }
                Spacer(modifier = Modifier.height(height = 2.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        fontSize = 10.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Light,
                        text = "Valor"
                    )
                    Text(
                        fontSize = 10.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Light,
                        text = "R$ 60.00"
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            OutlineContainer {
                Column {
                    Text(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Normal,
                        text = "Observações:"
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        fontSize = 10.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Light,
                        text = "Cliente solicitou instalação agendada para o dia seguinte. Todos os documentos foram entregues"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardDetalheVendaPreview() {
    VendedorcadastroclienteTheme {
        CardDetalheVenda()
    }
}