package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.OrgaoEmissorInvalidoException

class OrgaoEmissor(val value: String) {
    init {
        if (value.isNullOrEmpty()) throw OrgaoEmissorInvalidoException()
    }
}