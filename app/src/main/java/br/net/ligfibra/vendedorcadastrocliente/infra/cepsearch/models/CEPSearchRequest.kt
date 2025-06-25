package br.net.ligfibra.vendedorcadastrocliente.infra.cepsearch.models

import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.CEP

data class CEPSearchRequest(val cep: CEP, val format: String = "json")