package br.net.ligfibra.vendedorcadastrocliente.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.net.ligfibra.vendedorcadastrocliente.core.entities.ClienteLocalizacao
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState

@Composable
fun MapView(
    point: ClienteLocalizacao?,
    clienteNome: String,
    zoom: Float = 10f,
    cameraPositionState: CameraPositionState
) {
    LaunchedEffect(point) {
        point?.let {
            cameraPositionState.move(
                CameraUpdateFactory.newLatLngZoom(LatLng(it.lat, it.long), zoom)
            )
        }
    }
    GoogleMap(
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = false),
        modifier = Modifier
            .height(400.dp)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(10)
            )
            .clip(RoundedCornerShape(10))
    ) {
        LaunchedEffect(point) {
            point?.let {
                cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(
                    LatLng(it.lat, it.long), zoom
                ))
            }
        }

        point?.let {
            MarkerInfoWindow(
                state = MarkerState(position = LatLng(it.lat, it.long)),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .border(
                            BorderStroke(1.dp, Color.Black),
                            RoundedCornerShape(10)
                        )
                        .clip(RoundedCornerShape(10))
                        .background(Color.Blue)
                        .padding(20.dp)
                ) {
                    Text(clienteNome, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

@Preview
@Composable
private fun MapViewPreview() {
    VendedorcadastroclienteTheme {
        MapView(null, "Cliente teste", TODO(), TODO())
    }
}