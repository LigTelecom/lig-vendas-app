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
)
