package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import android.util.Log
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.ClienteTelefoneInvalidoException

class Telefone(val value: String) {
    init {
        Log.i("telefone", "telefone: $value")
        if (!isTelefoneFormatadoCorretamente(value))
            throw ClienteTelefoneInvalidoException()
    }

    private fun isTelefoneFormatadoCorretamente(telefone: String): Boolean {
        val regex = Regex("""^\(\d{2}\) \d{4,5}-\d{4}$""")
        return regex.matches(telefone)
    }

    override fun toString() = value

    companion object {
        fun isFormatado(telefone: String): Boolean =
            telefone.matches(Regex("""^\(\d{2}\) \d{4,5}-\d{4}$"""))

        fun formatarTelefoneComoIndexed(input: String): String {
            val raw = input.filter { it.isDigit() }.take(11)

            if (!raw.matches(Regex("\\d{10,11}")))
                throw ClienteTelefoneInvalidoException()

            return buildString {
                append("(")
                raw.forEachIndexed { index, c ->
                    when (index) {
                        1 -> {
                            append(c)
                            append(") ")
                        }
                        6 -> {
                            append(c)
                            append("-")
                        }
                        else -> append(c)
                    }
                }
            }
        }
    }


}