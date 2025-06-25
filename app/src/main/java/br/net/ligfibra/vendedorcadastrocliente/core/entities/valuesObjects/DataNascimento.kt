package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import android.os.Build
import androidx.annotation.RequiresApi
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.DataNascimentoMenorDataAtualException

@RequiresApi(Build.VERSION_CODES.O)
class DataNascimento(val value: String) {
    init {
        require(value.isNotBlank()) { throw DataNascimentoMenorDataAtualException() }
    }
}