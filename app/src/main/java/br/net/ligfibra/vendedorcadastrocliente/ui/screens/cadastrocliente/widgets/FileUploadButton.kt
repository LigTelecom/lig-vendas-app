package br.net.ligfibra.vendedorcadastrocliente.ui.screens.cadastrocliente.widgets

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Preview
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream


@Composable
fun FileUploadButton(
    text: String,
    icon: ImageVector,
    img: Bitmap?,
    onImageSelected: (Bitmap) -> Unit ) {
    val base64String = remember { mutableStateOf<String?>(null) }
    val bitmap = remember { mutableStateOf(img) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) {
        if (it == null) {
            Log.e("ImageDebug", "Falha ao capturar a imagem!")
            throw Exception("Falha ao capturar a imagem!")
        }

        bitmap.value = it
        base64String.value = bitmapToBase64(bitmap = it)
        onImageSelected(it)
        Log.i("ImageDebug", "${base64String.value}")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .clickable { launcher.launch(null) },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(height = 6.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .height(60.dp)
                .width(100.dp),
            textAlign = TextAlign.Center
        )
        if (bitmap != null) {
            ImageViewer(bitmap = bitmap.value)
        }
    }
}


fun bitmapToBase64(bitmap: Bitmap?): String {
    if (bitmap == null) throw Exception("Imagem bitmap null")
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}

@Preview
@Composable
fun FileUploadButtonPreview() {
    VendedorcadastroclienteTheme {
        val context = LocalContext.current
        val img: Bitmap = BitmapFactory.decodeResource(
            context.resources,
            android.R.drawable.ic_menu_upload
        )
        FileUploadButton(text = "teste", icon = Icons.Default.Preview, img, {})
    }
}