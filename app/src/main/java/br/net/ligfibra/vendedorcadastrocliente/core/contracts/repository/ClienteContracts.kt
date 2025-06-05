package br.net.ligfibra.vendedorcadastrocliente.core.contracts.repository

import br.net.ligfibra.vendedorcadastrocliente.core.entities.Cliente

public interface ClienteContracts {
    fun registrarCliente(cliente: Cliente): Void
    fun buscarCliente(id: Int): Cliente
}