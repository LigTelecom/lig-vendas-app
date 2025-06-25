package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteTelefoneInvalidoException

class Telefone(val value: String) {
    init {
        if (!value.matches(Regex("\\d{10,11}")))
            throw ClienteTelefoneInvalidoException()
    }
}