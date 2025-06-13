package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.net.ligfibra.vendedorcadastrocliente.core.entities.ClienteLocalizacao
import br.net.ligfibra.vendedorcadastrocliente.core.enums.MoradiaEnum
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.HorizontalDividerWithText
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.OutlineContainer
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.OutlinedContainerColors

@Composable
fun ClientAdressForm(next:() -> Unit, back:() -> Unit) {
    var cepInputValue by remember { mutableStateOf("") }
    var cidadeInputValue by remember { mutableStateOf("") }
    var enderecoInputValue by remember { mutableStateOf("") }
    var numeroCasaInputValue by remember { mutableStateOf("") }
    var bairroInputValue by remember { mutableStateOf("") }
    var localizacaoInputValue by remember { mutableStateOf<ClienteLocalizacao?>(null) }

    val tipoMoradias = MoradiaEnum.getTiposMoradia()
    val tipoMoradiSelected = remember {
        mutableStateOf(MoradiaEnum.Propria)
    }
    var expandedTiposMoradiaSelect = remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.Center) {
        HorizontalDividerWithText(text = "Endereço")

        Row {
            OutlinedTextField(
                value = cepInputValue,
                onValueChange = {
                    cepInputValue = it
                },
                modifier = Modifier
                    .padding(8.dp),
                label = {
                    Text("*CEP")
                }
            )

            OutlinedTextField(
                value = cidadeInputValue,
                onValueChange = {
                    cidadeInputValue = it
                },
                modifier = Modifier
                    .padding(8.dp),
                label = {
                    Text("*Cidade")
                }
            )
        }

        Row {
            OutlinedTextField(
                value = enderecoInputValue,
                onValueChange = {
                    enderecoInputValue = it
                },
                modifier = Modifier
                    .padding(8.dp),
                label = {
                    Text("*Endereço")
                }
            )

            OutlinedTextField(
                value = bairroInputValue,
                onValueChange = {
                    bairroInputValue = it
                },
                modifier = Modifier
                    .padding(8.dp),
                label = {
                    Text("*Bairro")
                }
            )
        }
        Row {

            OutlinedTextField(
                value = numeroCasaInputValue,
                onValueChange = {
                    numeroCasaInputValue = it
                },
                modifier = Modifier
                    .padding(8.dp),
                label = {
                    Text("Numero da Casa")
                }
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { expandedTiposMoradiaSelect.value = true }
                    .padding(8.dp),

                ) {
                OutlineContainer(
                    outlinedContainerColors = OutlinedContainerColors(
                        borderColor = Color.Transparent,
                        backgroundColor = Color.White)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Moradia", color = MaterialTheme.colorScheme.secondary,)
                        val arrowIcon = if (expandedTiposMoradiaSelect.value != true) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp
                        Icon(
                            imageVector = arrowIcon, contentDescription = null
                        )
                    }
                    Text(
                        text = tipoMoradiSelected.value.toString(),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                DropdownMenu(
                    expanded = expandedTiposMoradiaSelect.value, onDismissRequest = {
                        expandedTiposMoradiaSelect.value = false
                    }) {
                    tipoMoradias.forEach { moradia ->
                        DropdownMenuItem(text = {
                            Text(text = moradia.toString())
                        }, onClick = {
                            expandedTiposMoradiaSelect.value = false
                            tipoMoradiSelected.value = MoradiaEnum.valueOf(moradia)
                        })
                    }
                }
            }
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalDividerWithText(text = "Localização")

            Text("MAPA")

            Button(
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White,
                    disabledContainerColor = MaterialTheme.colorScheme.secondary,
                    disabledContentColor = Color.White,
                ),
                onClick = {
                    // TODO
                }
            ) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                Text(
                    text = "Localização atual",
                )
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
                onClick = {
                    Log.i("DocumentsUploadForm", "DocumentsUploadForm: testando")
                    back()
                },
                modifier = Modifier
                    .width(width = 110.dp)
                    .height(height = 46.dp),
            ) {
                Text(
                    text = "Voltar",
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = {
                    Log.i("DocumentsUploadForm", "DocumentsUploadForm: testando")
                    next()
                },
                modifier = Modifier
                    .width(width = 110.dp)
                    .height(height = 46.dp),
            ) {
                Text(
                    text = "Confirmar",
                )
            }
        }
    }
}

@Preview
@Composable
fun ClientAdressFormPreview() {
    VendedorcadastroclienteTheme {
        Surface {
            ClientAdressForm(TODO(), TODO())
        }
    }
}