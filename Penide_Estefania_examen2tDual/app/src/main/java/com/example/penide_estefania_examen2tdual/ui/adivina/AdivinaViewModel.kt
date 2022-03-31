package com.example.penide_estefania_examen2tdual.ui.adivina

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.penide_estefania_examen2tdual.R


class AdivinaViewModel(application: Application) : AndroidViewModel(application) {
    private val resources = application.resources

    var word = MutableLiveData("")
    var score = MutableLiveData(0)
    var wordListLiveData = MutableLiveData<MutableList<String>>()

    var juegoTerminado:Boolean=false


    init {
        Log.i("ViewModel", "ViewModel created!")
        wordListLiveData.setValue(resources.getStringArray(R.array.words).toMutableList())
        resetList()
        nextWord()
    }

    fun resetList() {
        wordListLiveData.value?.apply { shuffle() }
    }


    /**
     * Callback called when the ViewModel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        Log.i("ViewModel", "ViewModel destroyed!")
    }

    /** Methods for updating the UI **/
    fun onSkip() {
        if (!juegoTerminado) {
            score.value?.let { score.value = it - 1 }
        }
        resetList()
        nextWord()

    }

    fun onCorrect() {
        if (!juegoTerminado) {
            score.value?.let { score.value = it + 1 }
        }
        resetList()
        nextWord()

    }

    /**
     * Moves to the next word in the list.
     */
    fun nextWord() {
        if (!wordListLiveData.value.isNullOrEmpty()) {
            juegoTerminado=false
            word.setValue(wordListLiveData.value?.removeAt(0))// Select and remove a word from the list
        }else{
            juegoTerminado=true
        }
    }
}