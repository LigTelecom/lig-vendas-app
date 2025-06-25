package br.net.ligfibra.vendedorcadastrocliente.services.responses.cepsearch

import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Bairro
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Cidade
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.Rua

data class CEPEnderecoResponse(val cidade: Cidade, val rua: Rua, val bairro: Bairro)