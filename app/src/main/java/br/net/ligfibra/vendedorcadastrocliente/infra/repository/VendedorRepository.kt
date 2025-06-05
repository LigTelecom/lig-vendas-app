package br.net.ligfibra.vendedorcadastrocliente.infra.repository

import br.net.ligfibra.vendedorcadastrocliente.core.contracts.repository.VendedorContracts
import br.net.ligfibra.vendedorcadastrocliente.core.entities.Vendedor
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.client.IXC


class VendedorRepository(private val erp: IXC) : VendedorContracts {

    override suspend fun buscarVendedor(email: String): Result<Vendedor?> {
       return runCatching {
           erp.consultarUsuarioPorEmail(email)
               .getOrThrow()
               .let { vendedorResponse ->
                    Vendedor(
                        id = vendedorResponse.id.toInt(),
                        nome = vendedorResponse.nome,
                        email = vendedorResponse.email
                    )
               }
       }
    }
}