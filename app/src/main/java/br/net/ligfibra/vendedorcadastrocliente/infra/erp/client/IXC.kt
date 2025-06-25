package br.net.ligfibra.vendedorcadastrocliente.infra.erp.client

import android.util.Log
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.exceptions.CidadeNaoEncontradaException
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.exceptions.EmailNaoInformadoException
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.exceptions.IxcTokenVazioException
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.exceptions.IxcUrlVazioException
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.exceptions.UfNaoInformadoException
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.exceptions.UsuarioNaoEncontradoException
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.helpers.consultarEntidade
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.models.Cidade
import br.net.ligfibra.vendedorcadastrocliente.services.requests.erp.Query
import br.net.ligfibra.vendedorcadastrocliente.services.responses.vendedor.VendedorResponse
import br.net.ligfibra.vendedorcadastrocliente.utils.constants.Constants
import io.ktor.client.HttpClient
import io.ktor.client.request.headers
import io.ktor.client.request.post

import io.ktor.client.statement.bodyAsText

class IXC(
    val url: String = Constants.BASE_URL_IXC,
    val token: String = Constants.TOKEN_IXC,
    private val httpClient: HttpClient,
) {
    init {
        require(value = url.isNotBlank()) { throw IxcUrlVazioException() }
        require(value = url.isNotBlank()) { throw IxcTokenVazioException() }
    }

    suspend fun consultarUsuarios() {

        val response = httpClient.post(urlString = "${this.url}/v1/usuarios") {
            headers {
                append(name = "Content-Type", value = "application/json")
                append(name = "Authorization", value = "Basic $token")
                append(name = "ixcsoft", value = "listar")
            }
        }

        val responseText = response.bodyAsText()

        Log.i("onConsultarVendedores", "consultarVendedores: $responseText")

        httpClient.close()
    }

    suspend fun consultarUsuarioPorEmail(email: String): Result<VendedorResponse> {

        val query = Query(qtype = "usuarios.email", query = email, oper = "=", page = "1")

        return consultarEntidade(
            endpoint = "/v1/usuarios",
            query = query,
            validationFunctions = listOf({ validateEmail(email) }),
            notFoundException = UsuarioNaoEncontradoException(),
            httpClient = httpClient,
            url = url,
            token = token
        )
    }

    suspend fun consultarCidadesPorUF(uf: String): Result<Cidade> {

        val query = Query(qtype = "cidade.uf", query = uf, oper = "=", page = "1")

        return consultarEntidade(
            endpoint = "/v1/cidade",
            query = query,
            validationFunctions = listOf({ validateUf(uf) }),
            notFoundException = CidadeNaoEncontradaException(),
            httpClient = httpClient,
            url = url,
            token = token
        )
    }

    private fun validateUf(uf: String): Unit {
        if (!uf.isNotBlank()) throw UfNaoInformadoException()
    }

    private fun validateEmail(email: String): Unit {
        if (!email.isNotBlank()) throw EmailNaoInformadoException()
    }

}