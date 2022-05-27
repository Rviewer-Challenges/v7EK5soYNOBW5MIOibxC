package com.jarrod.memorygame.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
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
                binding.chronometer.start()
            }


            view.postDelayed({
                cardsViewModel.updateMoves()
            },500)
            cardsViewModel.updateMoves()
        }



    }
}