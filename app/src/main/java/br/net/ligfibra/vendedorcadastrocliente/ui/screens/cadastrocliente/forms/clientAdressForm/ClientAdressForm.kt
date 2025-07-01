package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientAdressForm

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import br.net.ligfibra.vendedorcadastrocliente.core.entities.ClienteLocalizacao
import br.net.ligfibra.vendedorcadastrocliente.core.enums.LocalidadesEnum
import br.net.ligfibra.vendedorcadastrocliente.core.enums.MoradiaEnum
import br.net.ligfibra.vendedorcadastrocliente.core.validation.ValidationResult
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.FormViewModel
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import br.net.ligfibra.vendedorcadastrocliente.ui.utils.extensions.CEPVisualTransformation
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.HorizontalDividerWithText
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.MapView
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.MapViewCameraSettings
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.MapViewPointSettings
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.MapViewSettings
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.OutlineContainer
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.OutlinedContainerColors
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@Composable
fun ClientAdressForm(
    viewModel: FormViewModel,
    next:() -> Unit,
    back:() -> Unit) {

    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val alagoas = LatLng(-9.763890555988786, -36.61089261823257)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(alagoas, 12f)
    }

    val enderecoState = viewModel.enderecoFormState

    val tipoMoradias = MoradiaEnum.getTiposMoradia()
    var expandedTiposMoradiaSelect = remember { mutableStateOf(false) }

    val tipoLocalidades = LocalidadesEnum.getTiposLocalidade()
    var expandedTiposLocalidadeSelect = remember { mutableStateOf(false) }

    var scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.verticalScroll(state = scrollState)
    ) {
        HorizontalDividerWithText(text = "Endereço")

        Row {
            Column {
                OutlinedTextField(
                    value = enderecoState.cep.value,
                    onValueChange = { viewModel.enderecoFormState = viewModel.enderecoFormState
                        .setCep(it) },
                    label = { Text("*CEP") },
                    isError = enderecoState.cep.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp),
                    visualTransformation = CEPVisualTransformation(),
                )
                if (enderecoState.cep.errorMessage.isNotEmpty()) {
                    Text(text = enderecoState.cep.errorMessage, color = Color.Red)
                }
            }

            Column {
                OutlinedTextField(
                    value = enderecoState.cidade.value,
                    onValueChange = { viewModel.enderecoFormState = viewModel.enderecoFormState.setCidade(it) },
                    label = { Text("*Cidade") },
                    isError = enderecoState.cidade.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp),
                )
                if (enderecoState.cidade.errorMessage.isNotEmpty()) {
                    Text(text = enderecoState.cidade.errorMessage, color = Color.Red)
                }
            }
        }

        Row {
            Column {
                OutlinedTextField(
                    value = enderecoState.rua.value,
                    onValueChange = { viewModel.enderecoFormState = viewModel.enderecoFormState.setRua(it) },
                    label = { Text("*Rua") },
                    isError = enderecoState.rua.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp),
                )
                if (enderecoState.rua.errorMessage.isNotEmpty()) {
                    Text(text = enderecoState.rua.errorMessage, color = Color.Red)
                }
            }

            Column {
                OutlinedTextField(
                    value = enderecoState.bairro.value,
                    onValueChange = { viewModel.enderecoFormState = viewModel.enderecoFormState.setBairro(it) },
                    label = { Text("*Bairro") },
                    isError = enderecoState.bairro.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp),
                )
                if (enderecoState.bairro.errorMessage.isNotEmpty()) {
                    Text(text = enderecoState.bairro.errorMessage, color = Color.Red)
                }
            }
        }

        

        Row {
            Column {
                OutlinedTextField(
                    value = enderecoState.pontoReferencia.value,
                    onValueChange = { viewModel.enderecoFormState = viewModel.enderecoFormState
                        .setPontoReferencia(it) },
                    label = { Text("*Ponto de referência") },
                    isError = enderecoState.pontoReferencia.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp),
                )
                if (enderecoState.pontoReferencia.errorMessage.isNotEmpty()) {
                    Text(text = enderecoState.pontoReferencia.errorMessage, color = Color.Red)
                }
            }

            Column {
                OutlinedTextField(
                    value = enderecoState.complemento.value,
                    onValueChange = { viewModel.enderecoFormState = viewModel.enderecoFormState.setComplemento(it) },
                    label = { Text("Complemento") },
                    isError = enderecoState.complemento.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp),
                )
                if (enderecoState.complemento.errorMessage.isNotEmpty()) {
                    Text(text = enderecoState.complemento.errorMessage, color = Color.Red)
                }
            }
        }

        Row {
            Column {
                OutlinedTextField(
                    value = enderecoState.numeroCasa.value,
                    onValueChange = { viewModel.enderecoFormState = viewModel.enderecoFormState.setNumeroCasa(it) },
                    label = { Text("Numero da Casa") },
                    isError = enderecoState.numeroCasa.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp),
                )
                if (enderecoState.numeroCasa.errorMessage.isNotEmpty()) {
                    Text(text = enderecoState.numeroCasa.errorMessage, color = Color.Red)
                }
            }
        }

        Row {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { expandedTiposMoradiaSelect.value = true }
                    .width(300.dp)
                    .padding(8.dp),

                ) {
                OutlineContainer(
                    outlinedContainerColors = OutlinedContainerColors(
                        borderColor = Color.Transparent,
                        backgroundColor = Color.White)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Moradia", color = MaterialTheme.colorScheme.secondary)
                        val arrowIcon = if (expandedTiposMoradiaSelect.value != true) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp
                        Icon(
                            imageVector = arrowIcon, contentDescription = null
                        )
                    }
                    Text(
                        text = enderecoState.tipoMoradia.toString(),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                DropdownMenu(
                    expanded = expandedTiposMoradiaSelect.value, onDismissRequest = {
                        expandedTiposMoradiaSelect.value = false
                    }) {
                    tipoMoradias.forEach { moradia ->
                        DropdownMenuItem(text = { Text(text = moradia.toString()) },
                            onClick = {
                                expandedTiposMoradiaSelect.value = false
                                viewModel.enderecoFormState = viewModel.enderecoFormState.setTipoMoradia(MoradiaEnum.valueOf(moradia))
                            }
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { expandedTiposLocalidadeSelect.value = true }
                    .width(300.dp)
                    .padding(8.dp),

                ) {
                OutlineContainer(
                    outlinedContainerColors = OutlinedContainerColors(
                        borderColor = Color.Transparent,
                        backgroundColor = Color.White)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = "Moradia", color = MaterialTheme.colorScheme.secondary)
                        val arrowIcon = if (expandedTiposLocalidadeSelect.value != true) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp
                        Icon(
                            imageVector = arrowIcon, contentDescription = null
                        )
                    }
                    Text(
                        text = enderecoState.tipoLocalidade.value.localidade,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                DropdownMenu(
                    expanded = expandedTiposLocalidadeSelect.value, onDismissRequest = {
                        expandedTiposLocalidadeSelect.value = false
                    }) {
                    tipoLocalidades.forEach { localidade ->
                        DropdownMenuItem(text = { Text(text = LocalidadesEnum.getLocalidade(localidade)) },
                            onClick = {
                                expandedTiposMoradiaSelect.value = false
                                viewModel.enderecoFormState = viewModel.enderecoFormState.setTipoLocalidade(LocalidadesEnum.valueOf(localidade))
                            }
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalDividerWithText(text = "Localização")

            Box(modifier = Modifier.padding(vertical = 8.dp)) {
                MapView(
                    pointSettings = MapViewPointSettings(point = enderecoState.localizacao.value, "teste"),
                    settings = MapViewSettings(
                        height = 256.dp,
                        borderColor = MaterialTheme.colorScheme.secondary
                    ),
                    cameraSettings = MapViewCameraSettings(
                        positionState = cameraPositionState,
                        zoom = 12f
                    )
                )
            }

            Button(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledContentColor = Color.White,
                ),
                onClick = {
                    getCurrentUserLocation(context, fusedLocationClient) {
                        location -> viewModel.enderecoFormState = viewModel.enderecoFormState.setLocalizacao(location)
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                Text(text = "Localização atual")
            }
        }

        Row(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledContentColor = Color.White,
                ),
                onClick = { back() },
                modifier = Modifier
                    .width(width = 110.dp)
                    .height(height = 46.dp),
            ) {
                Text(text = "Voltar")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    viewModel.viewModelScope.launch {
                        val result = viewModel.validateAddressForm()
                        when (result) {
                            is ValidationResult.Success -> next()
                            is ValidationResult.Error -> {}
                        }
                    }
                },
                modifier = Modifier
                    .width(width = 110.dp)
                    .height(height = 46.dp),
            ) {
                Text(text = "Confirmar")
            }
        }
    }
}

@SuppressLint("MissingPermission")
fun getCurrentUserLocation(
    context: Context,
    fusedLocationProviderClient: FusedLocationProviderClient,
    onLocationReceived: (ClienteLocalizacao?) -> Unit
) {
    fusedLocationProviderClient.getCurrentLocation(
        Priority.PRIORITY_HIGH_ACCURACY,
        null
    ).addOnSuccessListener { location ->
        if (location != null) {
            val latitude = location.latitude
            val longitude = location.longitude
            val clienteLocalizacao = ClienteLocalizacao(lat = latitude, long = longitude)
            Log.d("Location", "Lat: $latitude, Long: $longitude")
            onLocationReceived(clienteLocalizacao)
        } else {
            onLocationReceived(null)
        }
    }
}

@Preview
@Composable
fun ClientAdressFormPreview() {
    VendedorcadastroclienteTheme {
        Surface {
            ClientAdressForm(TODO(), TODO(), TODO())
        }
    }
}
