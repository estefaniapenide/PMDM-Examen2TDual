package com.example.penide_estefania_examen2tdual.ui.tres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.example.penide_estefania_examen2tdual.R
import com.example.penide_estefania_examen2tdual.databinding.FragmentGanadorBinding

class GanadorFragment : Fragment() {

    private var _binding: FragmentGanadorBinding? = null
    private val binding get() = _binding!!

    private val navGraphViewModel : TableroViewModel by navGraphViewModels<TableroViewModel>(R.id.mobile_navigation) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGanadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navGraphViewModel.getGanador()?.observe(viewLifecycleOwner){
            binding.tvGanadorResultado.text=it.toString()
        }
        /*binding.tvGanadorLabel.visibility=View.INVISIBLE
        binding.tvGanadorResultado.visibility=View.INVISIBLE

        var ganador=GanadorFragmentArgs.fromBundle(requireArguments()).ganador
        if(ganador!=""){
            binding.tvGanadorLabel.visibility=View.VISIBLE
            binding.tvGanadorResultado.visibility=View.VISIBLE
            binding.tvGanadorResultado.text=ganador
        }*/

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}