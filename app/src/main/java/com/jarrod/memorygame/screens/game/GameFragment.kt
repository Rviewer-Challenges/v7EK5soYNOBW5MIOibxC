package com.jarrod.memorygame.screens.game

import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.afollestad.materialdialogs.MaterialDialog
import com.jarrod.memorygame.R
import com.jarrod.memorygame.databinding.FragmentGameBinding
import com.jarrod.memorygame.models.Cards
import com.jarrod.memorygame.prefs.UserApplication.Companion.prefs
import com.jarrod.memorygame.screens.game.viewholder.Adapter
import com.jarrod.memorygame.screens.game.viewholder.ViewModel

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private var firstClick = true
    private val feedAdapter = Adapter()
    private val cardsViewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs.saveMoves(0)
        cardsViewModel.updateMoves()

        cardsViewModel.moves.observe(this, Observer {
            binding.tvMoves.text = it.toString()
        })



    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvMoves.text = prefs.getMoves().toString()

        val rvCards = binding.rvCards

        rvCards.adapter = feedAdapter

        rvCards.layoutManager = GridLayoutManager(context, prefs.getDifficultColumns().toInt())
        LinearSnapHelper().attachToRecyclerView(rvCards)
        cardsViewModel.cardsList.observe(
            viewLifecycleOwner
        ) {
            feedAdapter.addAll(it as MutableList<Cards>)
        }
        cardsViewModel.updateCards()


        feedAdapter.setOncardItemClickListener {
            prefs.saveTime(binding.chronometer.text.toString())
            if (firstClick){
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    binding.chronometer.isCountDown = true
//                }
//                binding.chronometer.base = SystemClock.elapsedRealtime() - (1 * 60000 + 0 * 1000)
                binding.chronometer.onChronometerTickListener = Chronometer.OnChronometerTickListener {
                    if (binding.chronometer.text == "01:00"){
                        binding.chronometer.stop()
                        MaterialDialog(requireContext()).show {
                            title(text = "We we we :c!")
                            navController = Navigation.findNavController(view)

                            navController.navigate(R.id.action_gameFragment_to_menuFragment)



                            message(text = "You loose, with ${prefs.getMoves()} move(s)")
                        }
                    }



                }

                binding.chronometer.start()
            }


            view.postDelayed({
                cardsViewModel.updateMoves()
            },500)
            cardsViewModel.updateMoves()
        }



    }



}