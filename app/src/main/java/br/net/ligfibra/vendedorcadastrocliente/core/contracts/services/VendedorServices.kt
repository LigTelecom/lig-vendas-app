package br.net.ligfibra.vendedorcadastrocliente.core.contracts.services

import br.net.ligfibra.vendedorcadastrocliente.core.entities.Vendedor

interface VendedorServices {
    suspend fun buscarVendedor(email: String): Result<Vendedor?>
}