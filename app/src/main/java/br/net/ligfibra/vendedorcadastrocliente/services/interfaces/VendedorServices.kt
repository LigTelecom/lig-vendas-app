package br.net.ligfibra.vendedorcadastrocliente.services.interfaces

import br.net.ligfibra.vendedorcadastrocliente.core.entities.Vendedor

interface VendedorServices {
    suspend fun buscarVendedor(email: String): Result<Vendedor?>
}