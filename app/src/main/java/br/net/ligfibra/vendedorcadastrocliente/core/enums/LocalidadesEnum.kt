package br.net.ligfibra.vendedorcadastrocliente.core.enums

enum class LocalidadesEnum(val localidade: String) {
    ZONA_URBANA("Zona Urbana"),
    ZONA_RURAL("Zona Rural");

    companion object {
        fun getTiposLocalidade(): List<String> {
            return values().map { it.name }
        }

        fun getLocalidade(localidade: String): String {
           val localidadeEnum = LocalidadesEnum.valueOf(localidade)
            return localidadeEnum.localidade
        }
    }
}
