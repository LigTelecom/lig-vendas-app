package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientAdressForm

import br.net.ligfibra.vendedorcadastrocliente.core.entities.ClienteLocalizacao
import br.net.ligfibra.vendedorcadastrocliente.core.enums.MoradiaEnum
import br.net.ligfibra.vendedorcadastrocliente.ui.utils.DataField

data class EnderecoFormState(
    val cep: DataField<String> = DataField("", ""),
    val rua: DataField<String> = DataField("", ""),
    val bairro: DataField<String> = DataField("", ""),
    val cidade: DataField<String> = DataField("", ""),
    val complemento: DataField<String> = DataField("", ""),
    val numeroCasa: DataField<String> = DataField("S/N", ""),
    val pontoReferencia: DataField<String> = DataField("", ""),
    val tipoMoradia: MoradiaEnum = MoradiaEnum.Propria,
    val localizacao: DataField<ClienteLocalizacao?> = DataField(
        value = ClienteLocalizacao(0.0, 0.0),
        errorMessage = ""
    )
) {
    fun setCep(novoValor: String) = copy(cep = cep.copy(value = novoValor))
    fun setRua(novoValor: String) = copy(rua = rua.copy(value = novoValor))
    fun setBairro(novoValor: String) = copy(bairro = bairro.copy(value = novoValor))
    fun setCidade(novoValor: String) = copy(cidade = cidade.copy(value = novoValor))
    fun setComplemento(novoValor: String) = copy(complemento = complemento.copy(value = novoValor))
    fun setNumeroCasa(novoValor: String) = copy(numeroCasa = numeroCasa.copy(value = novoValor))
    fun setPontoReferencia(novoValor: String) = copy(pontoReferencia = pontoReferencia.copy(value = novoValor))
    fun setTipoMoradia(novoValor: MoradiaEnum) = copy(tipoMoradia = novoValor)
    fun setLocalizacao(novoValor: ClienteLocalizacao?) = copy(localizacao = localizacao.copy(value = novoValor))
}
