package br.net.ligfibra.vendedorcadastrocliente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import br.net.ligfibra.vendedorcadastrocliente.ui.navigation.AppNavGraph
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme


var shouldShowCamera: MutableState<Boolean> = mutableStateOf<Boolean>(value = false)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        enableEdgeToEdge()

        setContent {
            VendedorcadastroclienteTheme {
               Surface(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(MaterialTheme.colorScheme.primary)
               ) {
                   val navController = rememberNavController()
                   AppNavGraph(navController)
               }
            }
        }
    }
}

