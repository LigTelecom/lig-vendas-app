package br.net.ligfibra.vendedorcadastrocliente.core.entities

import android.os.Build
import androidx.annotation.RequiresApi
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.DataVencimentoMaiorDataAtualException
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
public class ClienteContrato(
    val contrato: String = "Com fidelidade",
    val vencimento: LocalDate,
    val plano: Plano
) {
    init {
        require(vencimento.isAfter(LocalDate.now())) { throw DataVencimentoMaiorDataAtualException() }
    }
}