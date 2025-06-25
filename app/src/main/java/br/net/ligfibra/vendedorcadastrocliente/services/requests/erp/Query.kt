package br.net.ligfibra.vendedorcadastrocliente.services.requests.erp

import kotlinx.serialization.Serializable

@Serializable
class Query(
    val qtype: String = "",
    val query: String = "",
    val oper: String = "",
    val page: String = "",
    val rp: String = "",
    val sortname: String = "",
    val sortoder: String = ""
)