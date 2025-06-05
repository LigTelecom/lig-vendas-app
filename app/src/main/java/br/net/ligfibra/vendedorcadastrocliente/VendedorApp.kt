package br.net.ligfibra.vendedorcadastrocliente

import android.app.Application
import br.net.ligfibra.vendedorcadastrocliente.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VendedorApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@VendedorApp)
            modules(listOf(appModule))
        }
    }
}