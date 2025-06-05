package br.net.ligfibra.vendedorcadastrocliente.services.responses.vendedor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VendedorResponse(
    @SerialName("id")
    val id: String,
    @SerialName("nome")
    val nome: String,
    @SerialName("email")
    val email: String
)
