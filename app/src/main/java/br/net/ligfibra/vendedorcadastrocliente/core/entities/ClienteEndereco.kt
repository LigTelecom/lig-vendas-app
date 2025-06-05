package br.net.ligfibra.vendedorcadastrocliente.core.entities

import br.net.ligfibra.vendedorcadastrocliente.core.enums.MoradiaEnum
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.BairroInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.CepFormatoInvalido
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.CidadeNullException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.EnderecoInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.NumeroInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.PontoReferenciaInvalidoException

public class ClienteEndereco(
    val cep: String,
    val cidade: String,
    val endereco: String,
    val numero: String,
    val bairro: String,
    val pontoReferencia: String,
    val moradia: MoradiaEnum,
    val localizacao: ClienteLocalizacao
) {
    init {
        require(cep.matches(Regex("\\d{5}-\\d{3}"))) { throw CepFormatoInvalido() }
        require(cidade.isNotBlank()) { throw CidadeNullException() }
        require(endereco.isNotBlank()) { throw EnderecoInvalidoException() }
        require(numero.isNotBlank()) { throw NumeroInvalidoException() }
        require(bairro.isNotBlank()) { throw BairroInvalidoException() }
        require(pontoReferencia.isNotBlank()) { throw PontoReferenciaInvalidoException() }
    }
}

