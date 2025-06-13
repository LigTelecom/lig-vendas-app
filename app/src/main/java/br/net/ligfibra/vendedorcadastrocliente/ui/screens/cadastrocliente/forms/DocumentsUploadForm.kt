package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.forms

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.House
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.anyImageIsNull
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.widgets.FileUploadButton

@Composable
fun DocumentsUploadForm(
    next: () -> Unit
) {

    var imgRG by remember { mutableStateOf<Bitmap?>(null) }
    var imgComprovanteResidencia by remember { mutableStateOf<Bitmap?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        FileUploadButton(
            text = "RG",
            icon = Icons.Default.AccountBox,
            img = imgRG,
            onImageSelected = { imgRG = it }
        )
        FileUploadButton(
            text = "comprovante de residência",
            icon = Icons.Default.House,
            img = imgComprovanteResidencia,
            onImageSelected = { imgComprovanteResidencia = it }
        )
    }

    Button(
        onClick = {
            Log.i("DocumentsUploadForm", "DocumentsUploadForm: testando")
            next()
        },
        modifier = Modifier.height(height = 46.dp),
    ) {
        Text(
            text = if (!anyImageIsNull(imgRG, imgComprovanteResidencia)) "Pular" else "Confirmar",
        )
    }
}