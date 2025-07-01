package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientInfoForm

import br.net.ligfibra.vendedorcadastrocliente.ui.utils.DataField


data class ClienteInfoFormState(
    val clienteNome: DataField<String> = DataField("", ""),
    val dataNascimento: DataField<String> = DataField("", ""),
    val telefone: DataField<String> = DataField("", ""),
    val email: DataField<String> = DataField("", ""),
    val cpf: DataField<String> = DataField("", ""),
    val rg: DataField<String> = DataField("", ""),
    val orgaoEmissor: DataField<String> = DataField("", ""),
    val naturalidade: DataField<String> = DataField("", "")
)