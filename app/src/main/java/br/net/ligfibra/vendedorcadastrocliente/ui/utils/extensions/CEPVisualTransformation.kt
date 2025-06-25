package br.net.ligfibra.vendedorcadastrocliente.ui.utils.extensions

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CEPVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text.filter { it.isDigit() }.take(8)

        val formatted = buildString {
            raw.forEachIndexed { index, c ->
                append(c)
                if (index == 4 && raw.length > 5) append('-')
            }
        }

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return if (offset <= 5) offset
                else (offset + 1).coerceAtMost(formatted.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                return if (offset <= 5) offset
                else (offset - 1).coerceAtMost(raw.length)
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetTranslator)
    }
}