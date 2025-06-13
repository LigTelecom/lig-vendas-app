package br.net.ligfibra.vendedorcadastrocliente.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme

data class OutlinedContainerColors(
    val borderColor: Color,
    val backgroundColor: Color,
)

@Composable
fun OutlineContainer(
    outlinedContainerColors: OutlinedContainerColors = OutlinedContainerColors(
        borderColor = MaterialTheme.colorScheme.tertiary,
        backgroundColor = Color.Transparent,
    ),
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .border(
                border = BorderStroke(
                    width = 0.4.dp,
                    color = outlinedContainerColors.borderColor
                ),
                shape = RoundedCornerShape(size = 12.dp)

            )
            .clip(RoundedCornerShape(size = 12.dp))
            .background(outlinedContainerColors.backgroundColor)
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        content()
    }
}

@Preview
@Composable
private fun OutlineContainerPreview() {
    VendedorcadastroclienteTheme {
        Surface {
            OutlineContainer {
                Text("teste")
            }
        }
    }
}