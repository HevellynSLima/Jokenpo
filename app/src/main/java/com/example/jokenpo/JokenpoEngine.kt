package com.example.jokenpo

class JokenpoEngine {

    private val jogadas = arrayOf("Pedra", "Papel", "Tesoura")

    enum class Resultado {
        VITORIA, DERROTA, EMPATE
    }

    private fun getAIPlay(): String {
        val index = (0..2).random()
        return jogadas[index]
    }

    fun calcularResultado(jogadaUsuario: String): Pair<Resultado, String> {
        val jogadaRobo = getAIPlay()

        // EMPATE
        if (jogadaUsuario == jogadaRobo) {
            return Resultado.EMPATE to jogadaRobo
        }

        return when (jogadaUsuario) {
            "Pedra" ->
                if (jogadaRobo == "Tesoura") Resultado.VITORIA to jogadaRobo
                else Resultado.DERROTA to jogadaRobo

            "Papel" ->
                if (jogadaRobo == "Pedra") Resultado.VITORIA to jogadaRobo
                else Resultado.DERROTA to jogadaRobo

            "Tesoura" ->
                if (jogadaRobo == "Papel") Resultado.VITORIA to jogadaRobo
                else Resultado.DERROTA to jogadaRobo

            else -> Resultado.EMPATE to jogadaRobo
        }
    }
}