package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.clienteEndereco.CepFormatoInvalidoException

class CEP(private val value: String) {
    val cep: String = value.replace("-", "")

    init {
        if (!isValidCEP(cep)) { throw CepFormatoInvalidoException() }
    }

    override fun toString(): String {
        return cep
    }

    fun getStringRaw(): String {
        val raw = cep.replace("-", "")
        return cep
    }

    companion object {
        fun isValidCEP(cep: String): Boolean {
            val cepRegex = "^\\d{5}-?\\d{3}$"
            return cep.matches(Regex(cepRegex))
        }
    }
}