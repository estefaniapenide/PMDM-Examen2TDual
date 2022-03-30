package com.example.penide_estefania_examen2tdual.ui.adivina

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.example.penide_estefania_examen2tdual.R
import com.example.penide_estefania_examen2tdual.databinding.FragmentPuntuacionBinding


class PuntuacionFragment : Fragment() {

    private var _binding: FragmentPuntuacionBinding? = null
    private val binding get() = _binding!!


    private val navGraphViewModel : AdivinaViewModel by navGraphViewModels<AdivinaViewModel>(R.id.mobile_navigation) {
        defaultViewModelProviderFactory
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentPuntuacionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navGraphViewModel.score.observe(viewLifecycleOwner){
            binding.tvScore.text=it.toString()
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
        //reset()
        _binding=null
    }
}