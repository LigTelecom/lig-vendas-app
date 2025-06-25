package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

class ClienteContato(val telefone: Telefone, val email: Email) {
    constructor(telefone: String)
            : this(Telefone(telefone), Email(""))
}