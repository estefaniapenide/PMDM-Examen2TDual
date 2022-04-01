package com.example.penide_estefania_examen2tdual.ui.tres


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.penide_estefania_examen2tdual.databinding.FragmentTresBinding

class TresFragment : Fragment() {

    private var _binding: FragmentTresBinding? = null
    private val binding get() = _binding!!

    private lateinit var modelo: Tablero

    var miCurrentOrientation = this.getResources().getConfiguration().orientation

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (miCurrentOrientation != newConfig.orientation) {
            activity?.recreate(); // This recreate the activity if you can properly restore your activity state.
        }
    }

    /*override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        /*if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(activity, "landscape", Toast.LENGTH_SHORT).show()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(activity, "portrait", Toast.LENGTH_SHORT).show()
        }*/

    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGrid.setOnClickListener(::onCellClicked) // Asignamos comportamiento a los botones
        modelo = Tablero() // Instanciamos el modelo en el momento en que se lanza la activity

        binding.buttonReiniciar.setOnClickListener {
            reset()
            true
        }
    }
    /** Método que se lanza cuando se hace click en una celda */
    private fun onCellClicked(button: Button) {
        binding.buttonReiniciar.visibility=View.VISIBLE
        val tag = button.tag.toString().toCharArray()
        val row = tag[0].digitToInt()
        val col = tag[1].digitToInt()
        modelo.marcar(row, col)?.let{  //jugadorQueMovio ->
            button.text = it.toString()
            modelo.ganador?.let{ // Comprobamos si el movimiento ha generado un ganador
                var ganador=it.toString()
                findNavController().navigate(TresFragmentDirections.actionNavTresToGanadorFragment(ganador))
                //binding.winnerPlayerLabel.text = it.toString()
                //binding.winnerPlayerViewGroup.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun reset() {
        binding.buttonReiniciar.visibility = View.INVISIBLE
        // binding.winnerPlayerLabel.text = ""
        modelo.reiniciar()
        for (i in 0 until binding.buttonGrid.childCount) {
            (binding.buttonGrid.getChildAt(i) as Button).text = null
        }
    }

    /** Función de extensión que recibe como parámetro una lambda
     * Se exitende GridLayout para que tenga este método, el cual recorre todos los
     * Button que la componen (asunción) y para cada uno de ellos le asigna la función
     * pasada a su OnClickListener */
    private fun GridLayout.setOnClickListener(onClick: (Button) -> Unit ) {
        for (i in 0 until childCount) {
            val boton = getChildAt(i) as Button
            boton.setOnClickListener {
                onClick(boton)
            }
        }
    }
}