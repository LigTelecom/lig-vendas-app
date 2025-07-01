package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.PontoReferenciaInvalidoException

class PontoReferencia(val descricao: String) {
    init {
        validate()
    }

    override fun toString(): String {
        return descricao.toString()
    }

    fun validate() {
        if (!descricao.isNotBlank()) {
            throw PontoReferenciaInvalidoException()
        }
    }
}