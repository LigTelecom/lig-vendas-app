package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.entities.ClienteLocalizacao
import br.net.ligfibra.vendedorcadastrocliente.core.enums.MoradiaEnum

class Casa(
    val numero: NumeroCasa,
    val tipoMoradia: MoradiaEnum,
    val pontoReferencia: PontoReferencia,
    val localizacao: ClienteLocalizacao?
) {
    fun getPontoReferenciaDescricao(): String {
        return pontoReferencia.toString()
    }
}

