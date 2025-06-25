package br.net.ligfibra.vendedorcadastrocliente.ui.utils.extensions

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        if (text.text.isEmpty()) return TransformedText(text, OffsetMapping.Identity)

        val raw = text.text.filter { it.isDigit() }.take(11)
        val formatted = buildString {
            if (raw.isNotEmpty()) append('(')
            raw.forEachIndexed { index, c ->
                when (index) {
                    1 -> {
                        append(c)
                        append(") ")
                    }
                    6 -> append("$c-")
                    10 -> append(c)
                    else -> append(c)
                }
            }
        }

        val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 2) return offset + 1
                if (offset <= 7) return offset + 3
                if (offset <= 11) return offset + 4
                return formatted.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 3) return (offset - 1).coerceAtLeast(0)
                if (offset <= 9) return (offset - 3).coerceAtLeast(0)
                if (offset <= 15) return (offset - 4).coerceAtMost(raw.length)
                return raw.length
            }
        }

        return TransformedText(AnnotatedString(formatted), offsetTranslator)
    }
}