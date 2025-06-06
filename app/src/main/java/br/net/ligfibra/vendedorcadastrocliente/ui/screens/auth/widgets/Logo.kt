package br.net.ligfibra.vendedorcadastrocliente.ui.screens.auth.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.net.ligfibra.vendedorcadastrocliente.R

@Composable
fun Logo(size: Dp = 250.dp) {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        modifier = Modifier.size(size),
        contentDescription = "Logo"
    )
}