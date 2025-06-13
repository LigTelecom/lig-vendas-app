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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.net.ligfibra.vendedorcadastrocliente.core.enums.EstadoCivilEnum
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.HorizontalDividerWithText
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.OutlineContainer
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.OutlinedContainerColors
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientInfoForm(
    next: () -> Unit,
    back: () -> Unit) {

    var name by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember { mutableStateOf("") }

    var clientContactPhone by remember { mutableStateOf("") }
    var estadoCivil by remember { mutableStateOf("") }
    var clientEmail by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var rg by remember { mutableStateOf("") }
    var orgaoEmissor by remember { mutableStateOf("") }
    var naturalidade by remember { mutableStateOf("") }
    var expandedEstadoCivilSelect = remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.Center) {
        HorizontalDividerWithText(text = "Informações pessoais")
        Row {
            OutlinedTextField(
                value = name, onValueChange = {
                    name = it
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                label = { Text("*Nome Completo") },
            )
        }
        if (showDatePickerDialog) {
            DatePickerDialog(
                onDismissRequest = { showDatePickerDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            datePickerState
                                .selectedDateMillis?.let { millis ->
                                    selectedDate = millis.toBrazilianDate()
                                }
                            showDatePickerDialog = false
                        }
                    ) {
                        Text(text = "Escolher data")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        Row{
            OutlinedTextField(
                value = selectedDate,
                onValueChange = {},
                modifier = Modifier
                    .padding(8.dp)
                    .onFocusEvent {
                        if (it.isFocused) {
                            showDatePickerDialog = true
                            focusManager.clearFocus(force = true)
                        }
                    },
                label = {
                    Text("*Data de nascimento")
                },
                readOnly = true
            )

            val estadocivis = EstadoCivilEnum.getEstadoCivis()
            val estadoCivilSelected = remember {
                mutableStateOf(EstadoCivilEnum.Solteiro)
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { expandedEstadoCivilSelect.value = true }
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
                        Text(text = "Estado Civil", color = MaterialTheme.colorScheme.secondary,)
                        val arrowIcon = if (expandedEstadoCivilSelect.value != true) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp
                        Icon(
                            imageVector = arrowIcon, contentDescription = null
                        )
                    }
                    Text(
                        text = estadoCivilSelected.value.toString(),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                DropdownMenu(
                    expanded = expandedEstadoCivilSelect.value, onDismissRequest = {
                        expandedEstadoCivilSelect.value = false
                    }) {
                    estadocivis.forEach { estado ->
                        DropdownMenuItem(text = {
                            Text(text = estado.toString())
                        }, onClick = {
                            expandedEstadoCivilSelect.value = false
                            estadoCivilSelected.value = EstadoCivilEnum.valueOf(estado)
                        })
                    }
                }
            }
        }

        OutlinedTextField(
            value = naturalidade,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = {
                naturalidade = it
            },
            modifier = Modifier
                .padding(8.dp),
            label = {
                Text("*Naturalidade")
            }
        )
        HorizontalDividerWithText(text = "Informações para contato")
        Row {
            OutlinedTextField(
                value = clientContactPhone,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                onValueChange = {
                    clientContactPhone = it
                },
                modifier = Modifier
                    .padding(8.dp),
                label = {
                    Text("*Telefone")
                }
            )

            OutlinedTextField(
                value = clientEmail,
                onValueChange = {
                    clientEmail = it
                },
                modifier = Modifier
                    .padding(8.dp),
                label = {
                    Text("Email")
                }
            )
        }

        HorizontalDividerWithText(text = "Documentos")

        Row {
            OutlinedTextField(
                value = cpf,
                onValueChange = {
                    cpf = it
                },
                modifier = Modifier
                    .padding(8.dp),
                label = {
                    Text("*CPF")
                }
            )

            OutlinedTextField(
                value = rg,
                onValueChange = {
                    rg = it
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                label = {
                    Text("*RG")
                }
            )
        }

        Row {
            OutlinedTextField(
                value = orgaoEmissor,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                onValueChange = {
                    orgaoEmissor = it
                },
                modifier = Modifier
                    .padding(8.dp),
                label = {
                    Text("*Orgão Emissor")
                }
            )
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
                    text = "Próximo",
                )
            }
        }
    }
}

fun Long.toBrazilianDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt-br"))
    return dateFormat.format(Date(this))
}

@Preview
@Composable
fun ClientInfoFormPreview() {
    VendedorcadastroclienteTheme {
        ClientInfoForm({}, {})
    }
}