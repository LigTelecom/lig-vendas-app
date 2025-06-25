package br.net.ligfibra.vendedorcadastrocliente.services.services

import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Bairro
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.CEP
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Cidade

import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Rua
import br.net.ligfibra.vendedorcadastrocliente.core.validation.ValidationResult
import br.net.ligfibra.vendedorcadastrocliente.infra.cepsearch.client.ViaCEP
import br.net.ligfibra.vendedorcadastrocliente.infra.cepsearch.models.CEPSearchRequest
import br.net.ligfibra.vendedorcadastrocliente.services.interfaces.CEPSearchServices
import br.net.ligfibra.vendedorcadastrocliente.services.responses.cepsearch.CEPEnderecoResponse

class CEPSearchServicesImpl(private val viaCEP: ViaCEP): CEPSearchServices {
    override suspend fun buscarEnderecoCEP(cep: CEP): ValidationResult<CEPEnderecoResponse?> {
        val cepEnderecoBody = viaCEP.consultarCEP(CEPSearchRequest(cep, "json"))
        return cepEnderecoBody?.let {
            val cidade = Cidade(it.localidade)
            val rua = Rua(it.logradouro)
            val bairro = Bairro(it.bairro, cep)

            ValidationResult.Success(
                CEPEnderecoResponse(cidade = cidade, rua = rua, bairro = bairro)
            )
        } ?: ValidationResult.Error("CEP não encontrado")
    }
}