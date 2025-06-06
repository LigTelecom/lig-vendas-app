package br.net.ligfibra.vendedorcadastrocliente.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.net.ligfibra.vendedorcadastrocliente.ui.screens.auth.widgets.Logo
import br.net.ligfibra.vendedorcadastrocliente.ui.theme.VendedorcadastroclienteTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@Composable
fun AuthScreen(
    onEnterClick: () -> Unit,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel = koinViewModel()
) {

    val authState by authViewModel.authState.collectAsState()
    val resultado = remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onEnterClick()
        }
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
        ) {
        var email by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier.padding(horizontal = 64.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(170.dp))
            Logo(450.dp)
            OutlinedTextField(
                value = email, onValueChange = {
                    email = it
                },
                Modifier
                    .padding(8.dp)
                    .height(82.dp)
                    .fillMaxWidth(),
                label = { Text("Email", fontSize = 18.sp) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    focusedTextColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedTextColor = MaterialTheme.colorScheme.tertiary
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Representa usuário",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            )

            var senha by remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = senha, onValueChange = {
                    senha = it
                },
                Modifier
                    .padding(8.dp)
                    .height(82.dp)
                    .fillMaxWidth(),
                label = { Text("Senha", fontSize = 18.sp) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    focusedTextColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedTextColor = MaterialTheme.colorScheme.tertiary
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Key,
                        contentDescription = "Representa senha",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            )

        }
        Column(modifier = Modifier.padding(horizontal = 61.dp)) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        authViewModel.buscarVendedor(email)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .height(82.dp)
                    .fillMaxWidth()

            ) {
                Text("Entrar", fontSize = 26.sp)
            }

            when (authState) {
                is AuthState.Error -> {
                    Text(
                        text = (authState as AuthState.Error).message,
                        color = Color.Red,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                AuthState.Success -> {
                    Text(
                        text = "Bem-vindo!",
                        color = Color.Green,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                else -> {
                }
            }
        }

        resultado.value?.let {
            Text(
                text = it,
                color = if (it.startsWith("Erro")) Color.Red else Color.Green,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center

            ) }
        }
    }

    @Preview
    @Composable
    private fun AuthScreenPreview() {
        VendedorcadastroclienteTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                AuthScreen(onEnterClick = {})
            }
        }
    }
