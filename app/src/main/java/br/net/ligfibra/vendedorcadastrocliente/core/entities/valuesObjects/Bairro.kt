package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.CepFormatoInvalidoException

class Bairro(val nome: String, cep: CEP) {
    init {
        require(!cep.cep.isNullOrBlank()) { throw CepFormatoInvalidoException() }
    }
}