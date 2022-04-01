package com.example.penide_estefania_examen2tdual.ui.tres

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TableroViewModel : ViewModel(){
    enum class Jugador {
        X, O
    }

    internal class Celda {
        var value: Jugador? =
            null // Cada celda puede estar vacía o con el valor de un jugador ('O' o 'X')
    }

    private val celdas = Array(3) {
        arrayOfNulls<Celda>(
            3
        )
    } // El tablero se compone de 3x3 celdas


    private var ganador: MutableLiveData<Jugador?>? = MutableLiveData(Jugador.X)
    fun getGanador(): MutableLiveData<Jugador?>? {
        return ganador
    }

    private enum class GameState {
        JUGANDO, TERMINADO
    }

    private var estado: GameState? = null

    private var jugadorEnTurno: Jugador? = null


    init {
        reiniciar()
    }


    fun reiniciar() {
        clearCells()
        ganador = null
        jugadorEnTurno = Jugador.X
        estado = GameState.JUGANDO
    }

    /**
     * Método que marca la celda indicada por los párametros con el caracter del jugador en turno.
     *
     * @param row 0..2
     * @param col 0..2
     * @return (devuelve) el jugador en turno o null si no se ha podido marcar (celda inválida o partida acabada)
     */
    fun marcar(row:Int, col:Int): Jugador? {
        //Definir aquí otras row y col auxiliares para asignales el valor que se observa en las que se pasa por parámetro
        if (estado == GameState.TERMINADO) return null // No se sigue marcando si el juego ha terminado
        if (!isValida(row, col)) return null // Celda inválida (la vista ya no debería permitirlo
        val jugadorQueMovio = jugadorEnTurno
        celdas[row][col]!!.value = jugadorQueMovio
        if (isMovimientoGana(jugadorQueMovio, row, col)) {
            estado = GameState.TERMINADO
            ganador?.setValue(jugadorQueMovio)
        } else {
            cambiarTurno() // Cambia el Jugador en turno
        }
        return jugadorQueMovio
    }


    private fun clearCells() {
        for (i in 0..2) {
            for (j in 0..2) {
                celdas[i][j] = Celda()
            }
        }
    }

    private fun isValida(row: Int, col: Int): Boolean {
        return if (isOutOfBounds(row) || isOutOfBounds(col) ||
            isCeldaConValor(row, col)
        ) false else true
    }

    private fun isOutOfBounds(idx: Int): Boolean {
        return idx < 0 || idx > 2
    }

    private fun isCeldaConValor(row: Int, col: Int): Boolean {
        return celdas[row][col]!!.value != null
    }


    /**
     * Devuelve true si el movimiento gana
     */
    private fun isMovimientoGana(player: Jugador?, fila: Int, columna: Int): Boolean {
        return (celdas[fila][0]!!.value == player // 3-in-the-row
                && celdas[fila][1]!!.value == player && celdas[fila][2]!!.value == player
                ) || (celdas[0][columna]!!.value == player // 3-in-the-column
                && celdas[1][columna]!!.value == player && celdas[2][columna]!!.value == player
                ) || (fila == columna // 3-in-the-diagonal
                && celdas[0][0]!!.value == player && celdas[1][1]!!.value == player && celdas[2][2]!!.value == player
                ) || (fila + columna == 2 // 3-in-the-opposite-diagonal
                && celdas[0][2]!!.value == player && celdas[1][1]!!.value == player && celdas[2][0]!!.value == player)
    }

    private fun cambiarTurno() {
        jugadorEnTurno = if (jugadorEnTurno == Jugador.X) Jugador.O else Jugador.X
    }
}