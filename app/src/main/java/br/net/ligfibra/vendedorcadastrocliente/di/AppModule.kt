package br.net.ligfibra.vendedorcadastrocliente.di

import br.net.ligfibra.vendedorcadastrocliente.core.contracts.repository.VendedorContracts
import br.net.ligfibra.vendedorcadastrocliente.core.contracts.services.VendedorServices
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.client.IXC
import br.net.ligfibra.vendedorcadastrocliente.infra.repository.VendedorRepository
import br.net.ligfibra.vendedorcadastrocliente.services.services.VendedorServicesImpl
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.auth.AuthViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single {
        HttpClient(engineFactory = CIO) {
            install(plugin = Logging) {
                level = LogLevel.ALL
            }
            install(plugin = ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single { IXC(httpClient = get()) }
    single<VendedorContracts> { VendedorRepository(get()) }
    single<VendedorServices> { VendedorServicesImpl(get()) }
    viewModel { AuthViewModel(get()) }
}
