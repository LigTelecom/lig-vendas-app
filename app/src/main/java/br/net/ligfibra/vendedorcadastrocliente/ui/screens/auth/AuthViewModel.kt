package br.net.ligfibra.vendedorcadastrocliente.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.net.ligfibra.vendedorcadastrocliente.core.contracts.services.VendedorServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Success : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(private val vendedorServices: VendedorServices) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(value = AuthState.Idle)
    val authState = _authState.asStateFlow()

    fun buscarVendedor(email: String) {
        viewModelScope.launch {
            vendedorServices.buscarVendedor(email)
                .onSuccess { vendedor ->
                    _authState.value = AuthState.Success
                }
                .onFailure { erro ->
                    _authState.value = AuthState.Error(erro.message ?: "Erro desconhecido")
                }

        }
    }
}