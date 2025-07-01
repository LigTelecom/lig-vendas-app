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
) {
    fun setClienteNome(newName: String) = copy(clienteNome = clienteNome.copy(value = newName))
    fun setDataNascimento(newDataNascimento: String) = copy(dataNascimento = dataNascimento.copy(value = newDataNascimento))
    fun setTelefone(newTelefone: String) = copy(telefone = telefone.copy(value = newTelefone))
    fun setEmail(newEmail: String) = copy(email = email.copy(value = newEmail))
    fun setCpf(newCpf: String) = copy(cpf = cpf.copy(value = newCpf))
    fun setRg(newRg: String) = copy(rg = rg.copy(value = newRg))
    fun setOrgaoEmissor(newOrgaoEmissor: String) = copy(orgaoEmissor = orgaoEmissor.copy(value = newOrgaoEmissor))
    fun setNaturalidade(newNaturalidade: String) = copy(naturalidade = naturalidade.copy(value = newNaturalidade))
}