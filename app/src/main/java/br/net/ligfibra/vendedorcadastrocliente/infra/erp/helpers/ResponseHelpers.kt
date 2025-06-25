package br.net.ligfibra.vendedorcadastrocliente.infra.erp.helpers

import android.util.Log
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.models.ResponseResult
import br.net.ligfibra.vendedorcadastrocliente.services.requests.erp.Query
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.HttpClient

internal suspend inline fun <reified T> consultarEntidade(
    httpClient: HttpClient,
    token: String,
    url: String,
    endpoint: String,
    query: Query?,
    validationFunctions: List<() -> Unit>?,
    notFoundException: Exception
): Result<T> {
    val response = postQueryWithValidation(
        validationFunctions = validationFunctions,
        endpoint = endpoint,
        query = query,
        httpClient = httpClient,
        token = token,
        url = url
    )
    val responseSerialized = serializeHttpResponse<T>(response = response)
    val data = validarPresencaDeDados(
        serializedResponse = responseSerialized,
        notFoundException = notFoundException
    )
    return data
}

internal suspend fun postQueryWithValidation(
    httpClient: HttpClient,
    token: String,
    url: String,
    endpoint: String,
    query: Query?,
    validationFunctions: List<() -> Unit>?,
): HttpResponse {
    validationFunctions?.forEach { it.invoke() }
    val response = httpClient.post(urlString = url + endpoint) {
        headers {
            append(name = "Content-Type", value = "application/json")
            append(name = "Authorization", value = "Basic $token")
            append(name = "ixcsoft", value = "listar")
        }
        setBody(body = query)
    }
    return response
}

internal suspend inline fun <reified T> serializeHttpResponse(response: HttpResponse)
        : ResponseResult<T> {
    return try {
        response.body()
    } catch (e: Exception) {
        Log.e("Serialization", "${e.message}")
        throw Exception("Error ao tentar serializar o retorno da requisicao")
    }
}

internal inline fun <reified T> validarPresencaDeDados(
    serializedResponse: ResponseResult<T>,
    notFoundException: Exception
): Result<T> {
    return if (serializedResponse.registros.isEmpty())
        Result.failure(notFoundException)
    else
        Result.success(serializedResponse.registros.first())
}
