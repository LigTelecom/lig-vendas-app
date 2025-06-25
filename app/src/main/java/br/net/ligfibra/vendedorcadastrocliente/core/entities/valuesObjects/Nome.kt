package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteNomeInvalidoException

class Nome(val value: String) {
    init {
        require(value.isNotBlank()) { throw ClienteNomeInvalidoException() }
    }
}