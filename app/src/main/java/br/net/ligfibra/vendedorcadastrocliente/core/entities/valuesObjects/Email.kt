package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.EmailInvalidoException

class Email(val value: String?) {
    init {
        if (!value.isNullOrBlank() && !value.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")))
            throw EmailInvalidoException()
    }
}