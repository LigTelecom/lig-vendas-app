package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.NaturalidadeInvalidoException

class Naturalidade(value: String) {
    init {
        if (value.isNullOrEmpty()) throw NaturalidadeInvalidoException()
    }
}