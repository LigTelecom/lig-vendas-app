package br.net.ligfibra.vendedorcadastrocliente.core.entities

public class ClienteLocalizacao(var lat: Double, val long: Double) {

    fun hasValue(): Boolean {
        return !(lat == null && long == null)
    }

    override fun toString(): String {
        return "Localização (latitude: $lat, longitude: $long)"
    }
}