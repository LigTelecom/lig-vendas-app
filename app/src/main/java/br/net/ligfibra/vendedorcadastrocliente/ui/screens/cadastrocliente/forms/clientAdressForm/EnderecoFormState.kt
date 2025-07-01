package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientAdressForm

import br.net.ligfibra.vendedorcadastrocliente.core.entities.ClienteLocalizacao
import br.net.ligfibra.vendedorcadastrocliente.core.enums.LocalidadesEnum
import br.net.ligfibra.vendedorcadastrocliente.core.enums.MoradiaEnum
import br.net.ligfibra.vendedorcadastrocliente.ui.utils.DataField
import io.ktor.util.valuesOf

data class EnderecoFormState(
    val cep: DataField<String> = DataField(value = "", errorMessage = ""),
    val rua: DataField<String> = DataField(value = "", errorMessage = ""),
    val bairro: DataField<String> = DataField(value = "", errorMessage = ""),
    val cidade: DataField<String> = DataField(value = "", errorMessage = ""),
    val complemento: DataField<String> = DataField(value = "", errorMessage = ""),
    val numeroCasa: DataField<String> = DataField(value = "S/N", errorMessage = ""),
    val pontoReferencia: DataField<String> = DataField(value = "", errorMessage = ""),
    val tipoMoradia: MoradiaEnum = MoradiaEnum.Propria,
    val tipoLocalidade: DataField<LocalidadesEnum> = DataField(value = LocalidadesEnum.ZONA_URBANA, errorMessage = ""),
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
    fun setTipoLocalidade(novoValor: LocalidadesEnum) = copy(tipoLocalidade = tipoLocalidade.copy(value = novoValor))
    fun setLocalizacao(novoValor: ClienteLocalizacao?) = copy(localizacao = localizacao.copy(value = novoValor))
}
