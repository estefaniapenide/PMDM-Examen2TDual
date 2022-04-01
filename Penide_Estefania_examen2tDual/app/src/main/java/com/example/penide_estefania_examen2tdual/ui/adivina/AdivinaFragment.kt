package com.example.penide_estefania_examen2tdual.ui.adivina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.penide_estefania_examen2tdual.R
import com.example.penide_estefania_examen2tdual.databinding.FragmentAdivinaBinding
import com.google.android.material.snackbar.Snackbar

class AdivinaFragment : Fragment() {

    private var _binding: FragmentAdivinaBinding? = null
    private val binding get() = _binding!!

    private lateinit var modelo: AdivinaModel

    lateinit var lista:MutableList<String>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdivinaBinding.inflate(inflater, container, false)
        modelo = ViewModelProvider(this).get(AdivinaModel::class.java)
        reset()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btGotIt.setOnClickListener { onCorrect() }
        binding.btSkip.setOnClickListener { onSkip() }
        binding.btEndGame.setOnClickListener { onEndGame() }
        updateWordText()
        updateScoreText()
    }

    private fun onCorrect(){
        juegoTerminado()
        modelo.onCorrect()
        updateWordText()
        updateScoreText()
    }

    private fun onSkip(){
        juegoTerminado()
        modelo.onSkip()
        updateWordText()
        updateScoreText()
    }

    private fun onEndGame(){
        Toast.makeText(activity,R.string.game_finished,Toast.LENGTH_SHORT).show()
        val puntuacion=modelo.score.toString()
        findNavController().navigate(AdivinaFragmentDirections.actionNavAdivinaToPuntuacionFragment5(puntuacion))
    }

    private fun updateWordText(){
        binding.tvWord.text = modelo.word
    }

    private fun updateScoreText(){
        binding.tvScoreGame.text = modelo.score.toString()
    }

   private fun juegoTerminado(){
        if(modelo.wordList.isNullOrEmpty()){
          Snackbar.make(binding.root,R.string.no_words_left,Snackbar.LENGTH_LONG).setAction(R.string.reset) {
                reset()
            }
           .show()
        }
    }

    private fun reset(){
        modelo.score=0
        modelo.wordList = resources.getStringArray(R.array.words).toMutableList()
        modelo.resetList()
        modelo.nextWord()
        updateWordText()
        updateScoreText()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}