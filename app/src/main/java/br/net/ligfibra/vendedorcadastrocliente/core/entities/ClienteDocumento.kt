package br.net.ligfibra.vendedorcadastrocliente.core.entities

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.CpfInvalidoException

public class ClienteDocumento(
    val cpf: String,
    val rg: String,
    val orgaoEmissor: String = "SSP",
    val naturalidade: String
) {
    init {
        require(cpf.matches(Regex("\\d{11}"))) { throw CpfInvalidoException() }
    }
}