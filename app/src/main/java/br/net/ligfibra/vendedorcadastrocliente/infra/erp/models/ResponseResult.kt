package br.net.ligfibra.vendedorcadastrocliente.infra.erp.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseResult<T>(
    @SerialName("page")
    val page: String,
    @SerialName("total")
    val total: String,
    @SerialName("registros")
    val registros: List<T> = emptyList()
)