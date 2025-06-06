package br.net.ligfibra.vendedorcadastrocliente.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme

@Composable
fun AppBar(navController: NavController) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .height(86.dp)
            .fillMaxWidth()

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(size = 80.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AppBarPreview() {
    VendedorcadastroclienteTheme {
        AppBar(navController = TODO())
    }
}



