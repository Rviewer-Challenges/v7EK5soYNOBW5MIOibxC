package com.jarrod.memorygame.screens

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.jarrod.memorygame.R
import com.jarrod.memorygame.databinding.FragmentGameBinding
import com.jarrod.memorygame.databinding.FragmentSplashBinding

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    var isFront =true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        var scale = requireContext().resources.displayMetrics.density

//        val front = findViewById<TextView>(R.id.card_front) as TextView
//        val back =findViewById<TextView>(R.id.card_back) as TextView
//        val flip = findViewById<Button>(R.id.flip_btn) as Button

        binding.cardFront.cameraDistance = 8000 * scale
        binding.cardBack.cameraDistance = 8000 * scale


        // Now we will set the front animation
        front_anim = AnimatorInflater.loadAnimator(requireContext(), R.animator.front_animator) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(requireContext(), R.animator.back_animator) as AnimatorSet

        binding.flipBtn.setOnClickListener{
            if(isFront)
            {
                front_anim.setTarget(binding.cardBack)
                back_anim.setTarget(binding.cardFront)
                back_anim.start()
                front_anim.start()
                isFront = false

            }
            else
            {

                front_anim.setTarget(binding.cardFront)
                back_anim.setTarget(binding.cardBack)
                front_anim.start()
                back_anim.start()
                isFront = true
            }
        }
    }
}