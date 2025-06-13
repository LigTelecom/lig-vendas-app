package br.net.ligfibra.vendedorcadastrocliente.ui.utils

import androidx.compose.runtime.Composable

data class Form(
    val step: Int,
    val content: @Composable () -> Unit,
)