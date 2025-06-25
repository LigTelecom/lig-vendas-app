package br.net.ligfibra.vendedorcadastrocliente.infra.erp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cidade(
    @SerialName("codigo") val codigo: String,
    @SerialName("id") val id: String,
    @SerialName("nome") val nome: String,
    @SerialName("uf") val uf: String,
    @SerialName("cod_ibge") val codIbge: String,
    @SerialName("cod_siafi") val codSiafi: String,
    @SerialName("cod_cidade_nfse_forquilhinha_sc") val codCidadeNfseForquilhinhaSc: String,
    @SerialName("origem") val origem: String
)