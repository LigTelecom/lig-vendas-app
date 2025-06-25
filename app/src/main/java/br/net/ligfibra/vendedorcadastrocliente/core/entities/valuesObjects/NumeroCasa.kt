package br.net.ligfibra.vendedorcadastrocliente.core.entities.valuesObjects

class NumeroCasa(var numero: String) {
    init {
        require(numero.isNotEmpty()) { numero = "S/N" }
    }
}