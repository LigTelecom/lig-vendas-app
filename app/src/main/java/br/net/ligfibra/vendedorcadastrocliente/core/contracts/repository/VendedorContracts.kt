package br.net.ligfibra.vendedorcadastrocliente.core.contracts.repository

import br.net.ligfibra.vendedorcadastrocliente.core.entities.Vendedor

public interface VendedorContracts {
    suspend fun buscarVendedor(email: String): Result<Vendedor?>
}