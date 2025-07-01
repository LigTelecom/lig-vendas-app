package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.ComplementoEnderecoInvalidoException

class ComplementoEndereco(val descricao: String) {
    init {
        validate()
    }

    fun validate() {
        if (!descricao.isNotBlank()) {
            throw ComplementoEnderecoInvalidoException()
        }
    }
}