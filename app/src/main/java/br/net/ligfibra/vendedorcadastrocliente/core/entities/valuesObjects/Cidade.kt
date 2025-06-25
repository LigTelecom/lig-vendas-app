package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.CidadeInvalidaException

class Cidade(val nome: String) {
    init {
        require(nome.isNotBlank()) { throw CidadeInvalidaException() }
    }
}