package br.net.ligfibra.vendedorcadastrocliente.core.entities

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.plano.IdPlanoInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.plano.TituloPlanoNuloException

public class Plano(val id: Int, val titulo: String) {
    init {
        require(id > 0) { throw IdPlanoInvalidoException() }
        require(titulo.isNotBlank()) { throw TituloPlanoNuloException() }
    }

    override fun toString(): String {
        return "Plano(id=$id, titulo=$titulo)"
    }
}