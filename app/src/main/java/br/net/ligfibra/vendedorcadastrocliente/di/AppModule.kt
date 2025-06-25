package br.net.ligfibra.vendedorcadastrocliente.di

import android.os.Build
import androidx.annotation.RequiresApi
import br.net.ligfibra.vendedorcadastrocliente.core.contracts.repository.VendedorContracts
import br.net.ligfibra.vendedorcadastrocliente.infra.cepsearch.client.ViaCEP
import br.net.ligfibra.vendedorcadastrocliente.services.interfaces.VendedorServices
import br.net.ligfibra.vendedorcadastrocliente.infra.erp.client.IXC
import br.net.ligfibra.vendedorcadastrocliente.infra.repository.VendedorRepository
import br.net.ligfibra.vendedorcadastrocliente.services.interfaces.CEPSearchServices
import br.net.ligfibra.vendedorcadastrocliente.services.services.CEPSearchServicesImpl
import br.net.ligfibra.vendedorcadastrocliente.services.services.VendedorServicesImpl
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.auth.AuthViewModel
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientAdressForm.ClientAdressFormViewModel
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientInfoForm.ClientInfoFormsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


@RequiresApi(Build.VERSION_CODES.O)
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
            register(
                ContentType("text", "x-json"),
                KotlinxSerializationConverter(Json { ignoreUnknownKeys = true })
            )}
        }
    }

    single { IXC(httpClient = get()) }
    single { ViaCEP(httpClient = get()) }

    single<VendedorContracts> { VendedorRepository(get()) }
    single<VendedorServices> { VendedorServicesImpl(get()) }

    single<CEPSearchServices> { CEPSearchServicesImpl(get()) }

    viewModel { AuthViewModel(get()) }
    viewModel { ClientInfoFormsViewModel() }
    viewModel { ClientAdressFormViewModel(get()) }
}
