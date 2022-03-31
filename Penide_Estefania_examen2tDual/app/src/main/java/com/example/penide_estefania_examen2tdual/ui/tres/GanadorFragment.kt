package com.example.penide_estefania_examen2tdual.ui.tres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.penide_estefania_examen2tdual.databinding.FragmentGanadorBinding

class GanadorFragment : Fragment() {

    private var _binding: FragmentGanadorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGanadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvGanadorLabel.visibility=View.INVISIBLE
        binding.tvGanadorResultado.visibility=View.INVISIBLE

        var ganador=GanadorFragmentArgs.fromBundle(requireArguments()).ganador
        if(ganador!=""){
            binding.tvGanadorLabel.visibility=View.VISIBLE
            binding.tvGanadorResultado.visibility=View.VISIBLE
            binding.tvGanadorResultado.text=ganador
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}