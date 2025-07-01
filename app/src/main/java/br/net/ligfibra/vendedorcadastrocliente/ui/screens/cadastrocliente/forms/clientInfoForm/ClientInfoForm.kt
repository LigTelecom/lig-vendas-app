package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientInfoForm

import android.os.Build
import androidx.annotation.RequiresApi
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
import br.net.ligfibra.vendedorcadastrocliente.core.validation.ValidationResult
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.FormViewModel
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import br.net.ligfibra.vendedorcadastrocliente.ui.utils.extensions.CPFVisualTransformation
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.HorizontalDividerWithText
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.OutlineContainer
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.OutlinedContainerColors
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientInfoForm(
    viewModel: FormViewModel,
    next: () -> Unit,
    back: () -> Unit) {

    // Info Pessoal

    val focusManager = LocalFocusManager.current
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    

    val expandedEstadoCivilSelect = remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.Center) {
        HorizontalDividerWithText(text = "Informações pessoais")
        Row {
            Column {
                OutlinedTextField(
                    value = viewModel.clienteInfoFormState.clienteNome.value,
                    onValueChange = { viewModel.setClienteNome(it) },
                    label = { Text("*Nome Completo") },
                    isError = viewModel.clienteInfoFormState.clienteNome.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                )
                if (viewModel.clienteInfoFormState.clienteNome.errorMessage.isNotEmpty()) {
                    Text(text = viewModel.clienteInfoFormState.clienteNome.errorMessage, color = Color.Red)
                }
            }
        }
        if (showDatePickerDialog) {
            DatePickerDialog(
                onDismissRequest = { showDatePickerDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            datePickerState
                                .selectedDateMillis?.let { millis ->
                                    viewModel.setDataNascimento(millis.toBrazilianDate())
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
            Column {
                OutlinedTextField(
                    value = viewModel.clienteInfoFormState.dataNascimento.value,
                    onValueChange = { },
                    label = { Text("*Data de nascimento") },
                    readOnly = true,
                    modifier = Modifier
                        .padding(8.dp)
                        .onFocusEvent {
                            if (it.isFocused) {
                                showDatePickerDialog = true
                                focusManager.clearFocus(force = true)
                            }
                        },
                )
                if (viewModel.clienteInfoFormState.dataNascimento.errorMessage.isNotEmpty()) {
                    Text(text = viewModel.clienteInfoFormState.dataNascimento.errorMessage, color = Color.Red)
                }
            }

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
                        Text(text = "Estado Civil", color = MaterialTheme.colorScheme.secondary)
                        val arrowIcon = if (expandedEstadoCivilSelect.value != true) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp
                        Icon( imageVector = arrowIcon, contentDescription = null )
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
            value = viewModel.clienteInfoFormState.naturalidade.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            label = { Text("*Naturalidade") },
            onValueChange = { viewModel.setNaturalidade(it) },
            modifier = Modifier
                .padding(8.dp),
        )
        if (viewModel.clienteInfoFormState.naturalidade.errorMessage.isNotEmpty()) {
            Text(text = viewModel.clienteInfoFormState.naturalidade.errorMessage, color = Color.Red)
        }
        HorizontalDividerWithText(text = "Informações para contato")
        Row {
            Column {
                OutlinedTextField(
                    value = viewModel.clienteInfoFormState.telefone.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    onValueChange = { viewModel.setTelefone(it) },
                    label = { Text("*Telefone") },
                    isError = viewModel.clienteInfoFormState.telefone.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp),
                )
                if (viewModel.clienteInfoFormState.telefone.errorMessage.isNotEmpty()) {
                    Text(text = viewModel.clienteInfoFormState.telefone.errorMessage, color = Color.Red)
                }
            }
            Column {
                OutlinedTextField(
                    value = viewModel.clienteInfoFormState.email.value,
                    onValueChange = { viewModel.setEmail(it) },
                    label = { Text("Email") },
                    isError = viewModel.clienteInfoFormState.email.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp),
                )
                if (viewModel.clienteInfoFormState.email.errorMessage.isNotEmpty()) {
                    Text(text = viewModel.clienteInfoFormState.email.errorMessage, color = Color.Red)
                }
            }
        }

        HorizontalDividerWithText(text = "Documentos")

        Row {
            Column {
                OutlinedTextField(
                    value = viewModel.clienteInfoFormState.cpf.value,
                    onValueChange = { viewModel.setCpf(it) },
                    label = { Text("*CPF") },
                    isError = viewModel.clienteInfoFormState.cpf.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp),
                    visualTransformation = CPFVisualTransformation(),
                )
                if (viewModel.clienteInfoFormState.cpf.errorMessage.isNotEmpty()) {
                    Text(text = viewModel.clienteInfoFormState.cpf.errorMessage, color = Color.Red)
                }
            }

            Column {
                OutlinedTextField(
                    value = viewModel.clienteInfoFormState.rg.value,
                    onValueChange = { viewModel.setRg(it) },
                    label = { Text("*RG") },
                    isError = viewModel.clienteInfoFormState.rg.errorMessage.isNotEmpty(),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
                if (viewModel.clienteInfoFormState.rg.errorMessage.isNotEmpty()) {
                    Text(text = viewModel.clienteInfoFormState.rg.errorMessage, color = Color.Red)
                }
            }
        }

        Row {
            Column {
                OutlinedTextField(
                    value = viewModel.clienteInfoFormState.orgaoEmissor.value,
                    onValueChange = { viewModel.setOrgaoEmissor(it) },
                    label = { Text("*Orgão Emissor") },
                    modifier = Modifier
                        .padding(8.dp),
                )
                if (viewModel.clienteInfoFormState.orgaoEmissor.errorMessage.isNotEmpty()) {
                    Text(text = viewModel.clienteInfoFormState.orgaoEmissor.errorMessage, color = Color.Red)
                }
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
                    val result = viewModel.validateClienteInfoForm()
                    when (result) {
                            is ValidationResult.Success -> next()
                            is ValidationResult.Error -> {}
                    }
                },
                modifier = Modifier
                    .width(width = 110.dp)
                    .height(height = 46.dp),
            ) {
                Text(text = "Próximo")
            }
        }
    }
}



fun Long.toBrazilianDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt-br"))
    return dateFormat.format(Date(this))
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ClientInfoFormPreview() {
    VendedorcadastroclienteTheme {
        ClientInfoForm(TODO(),{}, {})
    }
}