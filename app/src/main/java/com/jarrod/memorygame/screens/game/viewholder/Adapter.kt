package com.jarrod.memorygame.screens.game.viewholder

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.jarrod.memorygame.R
import com.jarrod.memorygame.databinding.LayoutCardBinding
import com.jarrod.memorygame.models.Cards
import com.jarrod.memorygame.prefs.UserApplication.Companion.prefs

class Adapter(): RecyclerView.Adapter<Adapter.ViewHolder>() {

    val elementList:MutableList<Cards> = mutableListOf()
    private var oncardItemClickListener: ((card: Cards) -> Unit)? = null

    fun addAll(newElementList:MutableList<Cards>){
        elementList.clear()
        elementList.addAll(newElementList)
        notifyDataSetChanged()
    }

    fun setOncardItemClickListener(onCardItemClickListener: ((card: Cards) -> Unit)?) {
        this.oncardItemClickListener = onCardItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)

        return ViewHolder(layoutInflater.inflate(R.layout.layout_card, parent, false))
    }

    class ViewHolder( val view: View): RecyclerView.ViewHolder(view){
        val binding = LayoutCardBinding.bind(view)
        fun bind(card: Cards ) {

            if (prefs.getDifficult() == "easy") {
                binding.cardBack.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "4:8.5"
                }

                binding.cardFront.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "4:8.5"
                }
            }else if (prefs.getDifficult() == "medium") {
                binding.cardBack.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "4:5.5"
                }

                binding.cardFront.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "4:5.5"
                }
            } else {
                binding.cardBack.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "5:13"
                }

                binding.cardFront.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "5:13"
                }
            }



            binding.tvKana.text = card.kana
            binding.tvName.text = card.nameImg
            binding.imgBack.setImageResource(R.drawable.ic_baseline_all_inclusive_24)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isFront = true

        holder.bind(elementList[position])
        holder.itemView.setOnClickListener {
            oncardItemClickListener?.invoke(elementList[position])

            var scale = holder.view.context.resources.displayMetrics.density

            holder.binding.cardFront.cameraDistance = 8000 * scale
            holder.binding.cardBack.cameraDistance = 8000 * scale

            var front_anim: AnimatorSet = AnimatorInflater.loadAnimator(
                holder.view.context,
                R.animator.front_animator
            ) as AnimatorSet
            var back_anim: AnimatorSet = AnimatorInflater.loadAnimator(
                holder.view.context,
                R.animator.back_animator
            ) as AnimatorSet

            if (isFront){

                front_anim.setTarget(holder.binding.cardFront)
                back_anim.setTarget(holder.binding.cardBack)
                front_anim.start()
                back_anim.start()

                isFront = false
            }else {

                front_anim.setTarget(holder.binding.cardBack)
                back_anim.setTarget(holder.binding.cardFront)
                back_anim.start()
                front_anim.start()

                isFront = true
            }
        }



    }

    override fun getItemCount(): Int {
        return elementList.size
    }
}