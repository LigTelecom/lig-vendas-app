package br.net.ligfibra.vendedorcadastrocliente.core.entities

import android.os.Build
import androidx.annotation.RequiresApi
import br.net.ligfibra.vendedorcadastrocliente.core.enums.EstadoCivilEnum
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteNomeInvalidoException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.DataNascimentoMenorDataAtualException
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
public class Cliente(
    val id: Int,
    val nome: String,
    val nascimento: LocalDate,
    val vendedor: Vendedor,
    val contato: ClienteContato,
    val documentos: ClienteDocumento,
    val estadoCivil: EstadoCivilEnum,
    val contrato: ClienteContrato,
    val endereco: ClienteEndereco,

) {
    init {
        require(nome.isNotBlank()) { throw ClienteNomeInvalidoException() }
        require(nascimento.isBefore(LocalDate.now())) { throw DataNascimentoMenorDataAtualException() }
    }
}


