package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientAdressForm.ClientAdressForm
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientInfoForm.ClientInfoForm
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.DocumentsUploadForm
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientAdressForm.ClientAdressFormViewModel
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms.clientInfoForm.ClientInfoFormsViewModel
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import br.net.ligfibra.vendedorcadastrocliente.ui.utils.Form
import br.net.ligfibra.vendedorcadastrocliente.ui.widgets.GetPermissions
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastrarClienteScreen(modifier: Modifier = Modifier) {
    var step by remember { mutableStateOf<Int>(1) }

    val context = LocalContext.current

    var imgRG by remember { mutableStateOf<Bitmap?>(null) }
    var imgComprovanteResidencia by remember { mutableStateOf<Bitmap?>(null) }

    fun nextStep(): Unit {
        step += 1
    }

    fun backStep(): Unit {
        step -= 1
    }

    val FormsArray: List<Form> = listOf(
        Form(
            step = 1,
            content = {
                DocumentsUploadForm(::nextStep)
            },
        ),
        Form(
            step = 2,
            content = {
                val viewModelClientInfo: ClientInfoFormsViewModel = koinViewModel()
                ClientInfoForm(
                    viewModel = viewModelClientInfo,
                    next = ::nextStep,
                    back = ::backStep
                )
            },
        ),
        Form(
            step = 3,
            content = {
                val viewModelClientAdress: ClientAdressFormViewModel = koinViewModel()
                ClientAdressForm(
                    viewModel = viewModelClientAdress,
                    ::nextStep,
                    ::backStep
                )
            },
        )
    )

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiary),
    ) {
        GetPermissions()
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (form in FormsArray) {
                if (form.step == step) {
                    form.content()
                }
            }
        }
    }
}

fun anyImageIsNull(imgRG: Bitmap?, imgComprovanteResidencia: Bitmap?): Boolean {
    return !(imgRG == null && imgComprovanteResidencia == null)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CadastrarClienteScreenPreview() {
    VendedorcadastroclienteTheme {
        CadastrarClienteScreen()
    }
}
