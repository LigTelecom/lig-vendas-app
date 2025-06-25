package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteRGInvalidoException

class RG(val value: String) {
    init {
        if (value.isNullOrBlank()) {
            throw ClienteRGInvalidoException()
        }
    }
}