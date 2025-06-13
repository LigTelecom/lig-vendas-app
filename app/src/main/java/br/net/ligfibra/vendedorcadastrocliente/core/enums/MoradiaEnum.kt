package br.net.ligfibra.vendedorcadastrocliente.core.enums

public enum class MoradiaEnum {
    Propria,
    Alugada;

    companion object {
        fun getTiposMoradia(): List<String> {
            return values().map { it.name }
        }
    }
}