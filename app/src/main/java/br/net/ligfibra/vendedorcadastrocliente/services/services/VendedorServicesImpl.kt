package br.net.ligfibra.vendedorcadastrocliente.services.services

import br.net.ligfibra.vendedorcadastrocliente.core.contracts.repository.VendedorContracts
import br.net.ligfibra.vendedorcadastrocliente.core.entities.Vendedor
import br.net.ligfibra.vendedorcadastrocliente.core.contracts.services.VendedorServices

class VendedorServicesImpl(private val vendedorRepository: VendedorContracts) : VendedorServices {
        override suspend fun buscarVendedor(email: String): Result<Vendedor?> {
            return vendedorRepository.buscarVendedor(email).mapCatching { vendedor ->
                vendedor ?: throw Exception("Vendedor não encontrado!")
            }
        }
}