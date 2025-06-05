package br.net.ligfibra.vendedorcadastrocliente.utils.constants

import android.util.Base64

public class Constants {
    companion object {
        const val BASE_URL_IXC = "https://ixc.ligfibra.net.br/webservice"
        val TOKEN_IXC = Base64.encodeToString(
            "47:2d8e5c2d10991cdcd0003f67ca8144fa5712a4e9aa9e85e4808fc7238f174b0b".toByteArray(),
            Base64.NO_WRAP)
    }
}