package br.net.ligfibra.vendedorcadastrocliente.core.entities

import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ClienteContato
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ClienteDocumento
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ClienteEndereco
import br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects.ClienteInfoPessoal
import br.net.ligfibra.vendedorcadastrocliente.core.enums.EstadoCivilEnum


public class Cliente(
    val id: Int,
    val pessoalInfo: ClienteInfoPessoal,
    val vendedor: Vendedor,
    val contato: ClienteContato,
    val documentos: ClienteDocumento,
    val estadoCivil: EstadoCivilEnum,
    val contrato: ClienteContrato,
    val endereco: ClienteEndereco,
)


