package br.net.ligfibra.vendedorcadastrocliente.infra.cepsearch.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CEPSearchResponse(
    @SerialName("cep") val cep: String,
    @SerialName("logradouro") val logradouro: String,
    @SerialName("complemento") val complemento: String,
    @SerialName("bairro") val bairro: String,
    @SerialName("localidade") val localidade: String,
    @SerialName("uf") val uf: String,
)