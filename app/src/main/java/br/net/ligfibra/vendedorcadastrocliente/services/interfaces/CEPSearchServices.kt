package br.net.ligfibra.vendedorcadastrocliente.services.interfaces

import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.CEP
import br.net.ligfibra.vendedorcadastrocliente.core.validation.ValidationResult
import br.net.ligfibra.vendedorcadastrocliente.services.responses.cepsearch.CEPEnderecoResponse

interface CEPSearchServices {
    suspend fun buscarEnderecoCEP(cep: CEP): ValidationResult<CEPEnderecoResponse?>
}