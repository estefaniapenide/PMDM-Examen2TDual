package com.example.penide_estefania_examen2tdual.ui.adivina

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navGraphViewModels
import com.example.penide_estefania_examen2tdual.R
import com.example.penide_estefania_examen2tdual.databinding.FragmentPuntuacionBinding


class PuntuacionFragment : Fragment() {

    private var _binding: FragmentPuntuacionBinding? = null
    private val binding get() = _binding!!

    //private lateinit var modelo: AdivinaModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentPuntuacionBinding.inflate(inflater,container,false)
        //modelo = ViewModelProvider(this).get(AdivinaModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvScore.text = PuntuacionFragmentArgs.fromBundle(requireArguments()).puntuacion
    }


    /*private fun reset(){
        modelo.score=0
        modelo.wordList = resources.getStringArray(R.array.words).toMutableList()
        modelo.resetList()
        modelo.nextWord()
        //updateWordText()
        //updateScoreText()
    }*/

    override fun onDestroy() {
        super.onDestroy()
        //reset()
        _binding=null
    }
}