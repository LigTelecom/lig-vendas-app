package br.net.ligfibra.vendedorcadastrocliente.core.entities

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteTelefoneInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.EmailInvalidoException

class ClienteContato(val telefone: String, val email: String) {
    init {
        require(email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) {
            throw EmailInvalidoException()
        }
        require(telefone.matches(Regex("\\d{10,11}"))) {
            throw ClienteTelefoneInvalidoException()
        }
    }

    constructor(telefone: String) : this(telefone = telefone, email = "") {
        require(telefone.matches(Regex("\\d{10,11}"))) {
            throw ClienteTelefoneInvalidoException()
        }
    }
}