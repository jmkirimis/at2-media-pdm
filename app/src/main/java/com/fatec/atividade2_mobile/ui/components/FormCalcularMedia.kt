package com.fatec.atividade2_mobile.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.fatec.atividade2_mobile.model.Aluno

@Composable
fun FormCalcularMedia() {
    var aluno by remember { mutableStateOf<Aluno?>(null) }

    var nome by remember { mutableStateOf("") }
    var nota1 by remember { mutableStateOf("") }
    var nota2 by remember { mutableStateOf("") }
    var nota3 by remember { mutableStateOf("") }

    var errorNota1 by remember { mutableStateOf(false) }
    var errorNota2 by remember { mutableStateOf(false) }
    var errorNota3 by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    fun validarNota(valor: String): Boolean {
        val numero = valor.toDoubleOrNull() ?: return false
        return numero in 0.0..10.0
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 36.dp, start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Cálculadora de Média - João Marcos",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do aluno") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            item {
                Column {
                    OutlinedTextField(
                        value = nota1,
                        onValueChange = {
                            val valorFormatado = it.replace(',', '.')
                            nota1 = valorFormatado
                            errorNota1 = !validarNota(valorFormatado)
                        },
                        label = { Text("TP1") },
                        modifier = Modifier.width(100.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = errorNota1
                    )
                    if (errorNota1) Text(
                        "0 a 10",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            item {
                Column {
                    OutlinedTextField(
                        value = nota2,
                        onValueChange = {
                            val valorFormatado = it.replace(',', '.')
                            nota2 = valorFormatado
                            errorNota2 = !validarNota(valorFormatado)
                        },
                        label = { Text("TP2") },
                        modifier = Modifier.width(100.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = errorNota2
                    )
                    if (errorNota2) Text(
                        "0 a 10",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            item {
                Column {
                    OutlinedTextField(
                        value = nota3,
                        onValueChange = {
                            val valorFormatado = it.replace(',', '.')
                            nota3 = valorFormatado
                            errorNota3 = !validarNota(valorFormatado)
                        },
                        label = { Text("TP3") },
                        modifier = Modifier.width(100.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = errorNota3
                    )
                    if (errorNota3) Text(
                        "0 a 10",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = {
                if (nome.isBlank()) {
                    errorMessage = "Informe o nome do aluno"
                    return@Button
                }

                if (errorNota1 || errorNota2 || errorNota3 ||
                    nota1.isBlank() || nota2.isBlank() || nota3.isBlank()
                ) {
                    errorMessage = "Digite notas válidas entre 0 e 10"
                    return@Button
                }

                val n1 = nota1.toDouble()
                val n2 = nota2.toDouble()
                val n3 = nota3.toDouble()

                aluno = Aluno(
                    nome = nome,
                    notas = mutableListOf(n1, n2, n3)
                )

                errorMessage = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular Média")
        }

        aluno?.let { a ->
            val media = a.calcularMedia()
            val status = a.avaliarDesempenho()

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Aluno: ${a.nome}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Notas: TP1=${a.notas[0]}, TP2=${a.notas[1]}, TP3=${a.notas[2]}")
            Text(text = "Média: %.2f".format(media))
            Text(
                text = "Status: $status",
                color = when (status) {
                    "Reprovado" -> MaterialTheme.colorScheme.error
                    "Ótimo Aproveitamento" -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.secondary
                }
            )
        }
    }
}
