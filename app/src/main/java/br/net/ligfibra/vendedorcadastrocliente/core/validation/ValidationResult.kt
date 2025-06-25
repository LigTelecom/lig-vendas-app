package br.net.ligfibra.vendedorcadastrocliente.core.validation

sealed class ValidationResult<out T> {
    data class Success<T>(val result: T) : ValidationResult<T>()
    data class Error(val message: String) : ValidationResult<Nothing>()
}