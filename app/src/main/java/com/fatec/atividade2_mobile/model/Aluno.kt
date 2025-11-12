package com.fatec.atividade2_mobile.model

data class Aluno(
    var nome: String,
    var notas: MutableList<Double> = mutableListOf()
) {
    fun calcularMedia(): Double {
        return if (notas.size == 3) notas.average() else 0.0
    }

    fun avaliarDesempenho(): String {
        val media = calcularMedia()
        return when {
            media < 6.0 -> "Reprovado"
            media <= 9.0 -> "Aprovado"
            else -> "Ótimo Aproveitamento"
        }
    }
}
