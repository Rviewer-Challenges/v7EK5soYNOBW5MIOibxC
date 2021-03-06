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
import com.jarrod.memorygame.data.source.Katakana
import com.jarrod.memorygame.data.source.Temp
import com.jarrod.memorygame.databinding.FragmentDificultBinding
import com.jarrod.memorygame.databinding.FragmentGameBinding
import com.jarrod.memorygame.models.Cards
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

        var list: MutableList<Cards>
        if (prefs.getTypeList() == "hiragana") {
            list = Hiragana.hiraganaCards
        } else {
            list = Katakana.katakanaCards
        }


        binding.btnEasy.setOnClickListener {
            navController = Navigation.findNavController(view)

            prefs.saveDifficult("easy")
            prefs.saveDifficultColumns(4)

            val numberOfElements = 8

            val randomElements = list.asSequence().shuffled().take(numberOfElements).toList()

            Temp.gameCards = (randomElements + randomElements) as MutableList<Cards>

            navController.navigate(R.id.action_dificultFragment_to_gameFragment)
        }

        binding.btnMedium.setOnClickListener {
            navController = Navigation.findNavController(view)
            prefs.saveDifficult("medium")
            prefs.saveDifficultColumns(4)

            val numberOfElements = 24

            val randomElements = list.asSequence().shuffled().take(numberOfElements).toList()

            Temp.gameCards = randomElements as MutableList<Cards>

            navController.navigate(R.id.action_dificultFragment_to_gameFragment)
        }

        binding.btnHard.setOnClickListener {
            navController = Navigation.findNavController(view)
            prefs.saveDifficult("hard")
            prefs.saveDifficultColumns(6)

            val numberOfElements = 30

            val randomElements = list.asSequence().shuffled().take(numberOfElements).toList()

            Temp.gameCards = randomElements as MutableList<Cards>

            navController.navigate(R.id.action_dificultFragment_to_gameFragment)
        }
    }
}