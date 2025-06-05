package br.net.ligfibra.vendedorcadastrocliente.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

// Set of Material typography styles to start with
val Typography = Typography(
    bodyMedium = TextStyle(
        fontFamily = robotoMonoFamily,
        fontWeight = FontWeight.Normal,

    ),
    bodyLarge = TextStyle(
        fontFamily = robotoMonoFamily,
        fontWeight = FontWeight.Bold,

    ),
    headlineMedium = TextStyle(
        fontFamily = robotoMonoFamily,
        fontWeight = FontWeight.SemiBold,
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)