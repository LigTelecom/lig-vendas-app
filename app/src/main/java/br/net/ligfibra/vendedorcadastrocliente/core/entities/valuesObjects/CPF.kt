package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.cliente.CpfInvalidoException

class CPF(private val value: String) {
    init {
        if (!isValidCPF()) {
            throw CpfInvalidoException()
        }
    }


    private fun isValidCPF(): Boolean {
        val cleanCpf = value.filter { it.isDigit() }

        if (cleanCpf.length != 11 || cleanCpf.all { it == cleanCpf[0] }) return false

        val firstCheck = (0..8).sumOf { (cleanCpf[it].digitToInt() * (10 - it)) }
        val firstDigit = (firstCheck * 10 % 11).let { if (it == 10) 0 else it }

        val secondCheck = (0..9).sumOf { (cleanCpf[it].digitToInt() * (11 - it)) }
        val secondDigit = (secondCheck * 10 % 11).let { if (it == 10) 0 else it }

        return cleanCpf[9].digitToInt() == firstDigit && cleanCpf[10].digitToInt() == secondDigit
    }
}