package br.net.ligfibra.vendedorcadastrocliente.infra.erp.client

import android.util.Log
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.exceptions.EmailNaoInformadoException
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.exceptions.IxcTokenVazioException
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.exceptions.IxcUrlVazioException
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.exceptions.UsuarioNãoEncontrado
import br.net.ligfibra.vendedorcadastrocliente.services.requests.erp.Query
import br.net.ligfibra.vendedorcadastrocliente.services.responses.erp.ResponseResult
import br.net.ligfibra.vendedorcadastrocliente.services.responses.vendedor.VendedorResponse
import br.net.ligfibra.vendedorcadastrocliente.utils.constants.Constants

import io.ktor.client.HttpClient

import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText

import kotlinx.serialization.json.Json


class IXC(
    val url: String = Constants.BASE_URL_IXC,
    val token: String = Constants.TOKEN_IXC,
    private val httpClient: HttpClient,
) {
    init {
        require(url.isNotBlank()) { throw IxcUrlVazioException() }
        require(url.isNotBlank()) { throw IxcTokenVazioException() }
    }

    suspend fun consultarUsuarios() {

        val response = httpClient.post(this.url + "/v1/usuarios") {
            headers {
                append("Content-Type", "application/json")
                append("Authorization", "Basic $token")
                append("ixcsoft", "listar")
            }
        }

        val responseText = response.bodyAsText()

        Log.i("onConsultarVendedores", "consultarVendedores: $responseText")

        httpClient.close()
    }

    suspend fun consultarUsuarioPorEmail(email: String): Result<VendedorResponse> {

        if (!email.isNotBlank()) return Result.failure(EmailNaoInformadoException())

        val response = httpClient.post(this.url + "/v1/usuarios") {
            headers {
                append("Content-Type", "application/json")
                append("Authorization", "Basic $token")
                append("ixcsoft", "listar")
            }
            setBody(Query(
                "usuarios.email",
                email,
                "=",
                "1",
                "",
                "",
                ""
            ))
        }

        val responseText = response.bodyAsText()

        val json = Json {
            ignoreUnknownKeys = true
        }

        val responseObject: ResponseResult<VendedorResponse> = json.decodeFromString(
            ResponseResult.serializer(VendedorResponse.serializer()),
            responseText
        )

        if (responseObject.registros.isEmpty()) return Result.failure(UsuarioNãoEncontrado())

        return Result.success(responseObject.registros.first())
    }
}