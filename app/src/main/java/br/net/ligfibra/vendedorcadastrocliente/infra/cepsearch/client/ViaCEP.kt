package br.net.ligfibra.vendedorcadastrocliente.infra.cepsearch.client

import android.util.Log
import br.net.ligfibra.vendedorcadastrocliente.infra.cepsearch.exceptions.ViaCepUrlRequiredException
import br.net.ligfibra.vendedorcadastrocliente.infra.cepsearch.models.CEPSearchRequest
import br.net.ligfibra.vendedorcadastrocliente.infra.cepsearch.models.CEPSearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers


class ViaCEP(
    private val url: String = "https://viacep.com.br/ws",
    private val httpClient: HttpClient,
) {
    init {
        require(url.isNotBlank()) { throw ViaCepUrlRequiredException() }
    }

    suspend fun consultarCEP(request: CEPSearchRequest): CEPSearchResponse? {
        return try {
            val response = httpClient.get(
                urlString = "$url/${request.cep.getStringRaw()}/${request.format}/"
            ) {
                headers {
                    append("Content-Type", "application/json")
                }
            }.body<CEPSearchResponse>()
            Log.i("consultarCEP", "consultarCEP: ${response.localidade}")
            return response
        } catch (_: Exception) {
            null
        }
    }
}