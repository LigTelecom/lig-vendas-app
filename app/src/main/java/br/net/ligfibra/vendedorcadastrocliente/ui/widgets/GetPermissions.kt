package br.net.ligfibra.vendedorcadastrocliente.ui.widgets

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun GetPermissions() {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val cameraGranted = permissions[Manifest.permission.CAMERA] ?: false
        val storageGranted = permissions[Manifest.permission.READ_EXTERNAL_STORAGE] ?: false

        if (cameraGranted && storageGranted) {
            Log.d("PermissaoDebug", "Todas as permissões concedidas!")
        } else {
            Log.e("PermissaoDebug", "Alguma permissão foi negada!")
        }
    }



    LaunchedEffect(Unit) {
        val permissionsNeeded = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        val allGranted = permissionsNeeded.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }

        if (!allGranted) {
            launcher.launch(permissionsNeeded)
        }
    }





}