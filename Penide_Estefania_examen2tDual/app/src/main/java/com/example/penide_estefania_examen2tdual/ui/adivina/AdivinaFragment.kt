package com.example.penide_estefania_examen2tdual.ui.adivina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.penide_estefania_examen2tdual.R
import com.example.penide_estefania_examen2tdual.databinding.FragmentAdivinaBinding
import com.google.android.material.snackbar.Snackbar

class AdivinaFragment : Fragment() {

    private var _binding: FragmentAdivinaBinding? = null
    private val binding get() = _binding!!

    lateinit var lista:MutableList<String>


    private val navGraphViewModel : AdivinaViewModel by navGraphViewModels<AdivinaViewModel>(R.id.mobile_navigation) {
        defaultViewModelProviderFactory
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdivinaBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateWordText()
        updateScoreText()

        binding.btGotIt.setOnClickListener { onCorrect() }
        binding.btSkip.setOnClickListener { onSkip() }
        binding.btEndGame.setOnClickListener { onEndGame() }
    }

    private fun onCorrect(){
        juegoTerminado()
        navGraphViewModel.onCorrect()
    }

    private fun onSkip(){
        juegoTerminado()
        navGraphViewModel.onSkip()
    }

    private fun onEndGame(){
        Toast.makeText(activity,"Game has just finished",Toast.LENGTH_SHORT).show()
        findNavController().navigate(AdivinaFragmentDirections.actionNavAdivinaToPuntuacionFragment5())
    }

    private fun updateWordText(){
        navGraphViewModel.word.observe(viewLifecycleOwner){
            binding.tvWord.text=it
        }
    }

    private fun updateScoreText(){
        navGraphViewModel.score.observe(viewLifecycleOwner){
            binding.tvScoreGame.text=it.toString()
        }
    }


    private fun juegoTerminado(){
        navGraphViewModel.wordListLiveData.observe(viewLifecycleOwner){
            lista=it
        }
        if(lista.isNullOrEmpty()){
          Snackbar.make(binding.root,"No quedan palabras",Snackbar.LENGTH_LONG).setAction("REINICIAR") {
                reset()
            }
           .show()
        }
    }

    private fun reset(){
        navGraphViewModel.score.postValue(0)
        navGraphViewModel.wordListLiveData.setValue(resources.getStringArray(R.array.words).toMutableList())
        navGraphViewModel.resetList()
        navGraphViewModel.nextWord()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}