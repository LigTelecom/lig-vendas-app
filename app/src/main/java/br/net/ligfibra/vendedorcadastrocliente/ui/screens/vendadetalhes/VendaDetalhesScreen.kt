package br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.net.ligfibra.vendedorcadastrocliente.core.entities.ClienteLocalizacao
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.vendadetalhes.widgets.carddetalhe.CardDetalheVenda
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.AppBar
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun VendaDetalhesScreen(vendaId: String, navController: NavController) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 15f)
    }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
    ) {
    AppBar(navController)
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val point: ClienteLocalizacao? = ClienteLocalizacao(
                lat = -9.91071568300058,
                long = -36.35323035272515
            )
            CardDetalheVenda()
            Spacer(modifier = Modifier.height(12.dp))
            MapView(
                point = point,
                clienteNome = "Carla Souza",
                zoom = 10f,
                cameraPositionState = cameraPositionState
            )
        }
    }
}

@Preview
@Composable
private fun VendaDetalhesScreenPreview() {
    VendedorcadastroclienteTheme {
        VendaDetalhesScreen(navController = TODO(), vendaId = "1")
    }
}
