package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.RuaInvalidaException

class Rua(val nome: String) {
    init {
        require(nome.isNotBlank()) { throw RuaInvalidaException() }
    }

    override fun toString(): String {
        return nome.toString()
    }
}