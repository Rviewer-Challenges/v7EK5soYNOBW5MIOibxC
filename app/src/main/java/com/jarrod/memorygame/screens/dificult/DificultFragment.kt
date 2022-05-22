package com.jarrod.memorygame.screens.dificult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jarrod.memorygame.R
import com.jarrod.memorygame.data.source.Hiragana
import com.jarrod.memorygame.data.source.Temp
import com.jarrod.memorygame.databinding.FragmentDificultBinding
import com.jarrod.memorygame.databinding.FragmentGameBinding
import com.jarrod.memorygame.prefs.UserApplication.Companion.prefs

class DificultFragment : Fragment() {
    private var _binding: FragmentDificultBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDificultBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(context, prefs.getTypeList(), Toast.LENGTH_SHORT).show()

        Temp.gameCards = Hiragana.hiraganaCards//temp

        binding.btnEasy.setOnClickListener {
            navController = Navigation.findNavController(view)

            prefs.saveDifficut(0)
            navController.navigate(R.id.action_dificultFragment_to_gameFragment)
        }

        binding.btnMedium.setOnClickListener {
            navController = Navigation.findNavController(view)

            prefs.saveDifficut(1)
            navController.navigate(R.id.action_dificultFragment_to_gameFragment)
        }

        binding.btnHard.setOnClickListener {
            navController = Navigation.findNavController(view)

            prefs.saveDifficut(2)
            navController.navigate(R.id.action_dificultFragment_to_gameFragment)
        }
    }
}