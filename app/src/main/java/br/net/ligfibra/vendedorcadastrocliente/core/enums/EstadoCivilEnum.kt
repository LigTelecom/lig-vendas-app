package br.net.ligfibra.vendedorcadastrocliente.core.enums

public enum class EstadoCivilEnum {
    Casado,
    Divorciado,
    Solteiro,
    Viúvo;

    companion object {
        fun getEstadoCivis(): List<String> {
            return values().map { it.name }
        }
    }
}
