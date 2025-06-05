package br.net.ligfibra.vendedorcadastrocliente.core.entities

import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.localizacao.LatitudeInvalidaException
import br.net.ligfibra.vendedorcadastrocliente.core.exceptions.localizacao.LongitudeInvalidaException
import java.math.BigDecimal

public class ClienteLocalizacao(val lat: BigDecimal, val long: BigDecimal) {
    init {
        require(lat in BigDecimal.valueOf(-90)..BigDecimal.valueOf(90)) {
            throw LatitudeInvalidaException()
        }
        require(long in BigDecimal.valueOf(-180)..BigDecimal.valueOf(180)) {
            throw LongitudeInvalidaException()
        }
    }

    override fun toString(): String {
        return "Localização (latitude: $lat, longitude: $long)"
    }
}