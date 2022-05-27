package com.jarrod.memorygame.screens.game.viewholder

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.core.view.updateLayoutParams
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.jarrod.memorygame.R
import com.jarrod.memorygame.databinding.LayoutCardBinding
import com.jarrod.memorygame.models.Cards
import com.jarrod.memorygame.prefs.UserApplication.Companion.prefs

class Adapter() : RecyclerView.Adapter<Adapter.ViewHolder>() {

    val elementList: MutableList<Cards> = mutableListOf()
    private var oncardItemClickListener: ((card: Cards) -> Unit)? = null
    var listFlipped = mutableListOf<Int>()
    var numMatched = 0


    fun addAll(newElementList: MutableList<Cards>) {
        elementList.clear()
        elementList.addAll(newElementList)
        elementList.shuffle()
        notifyDataSetChanged()
    }

    fun setOncardItemClickListener(onCardItemClickListener: ((card: Cards) -> Unit)?) {
        this.oncardItemClickListener = onCardItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return ViewHolder(layoutInflater.inflate(R.layout.layout_card, parent, false))
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutCardBinding.bind(view)
        fun bind(card: Cards) {

            if (prefs.getDifficult() == "easy") {
                binding.cardBack.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "4:7.5"
                }

                binding.cardFront.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "4:7.5"
                }
            } else if (prefs.getDifficult() == "medium") {
                binding.cardBack.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "4:5"
                }

                binding.cardFront.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "4:5"
                }
            } else {
                binding.cardBack.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "5:11.5"
                }

                binding.cardFront.updateLayoutParams<ConstraintLayout.LayoutParams> {
                    dimensionRatio = "5:11.5"
                }
            }


            binding.tvKana.text = card.kana
            binding.tvName.text = card.nameImg

            binding.imgFront.setImageResource(card.srcImg)

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(elementList[position])
        holder.itemView.setOnClickListener {
            oncardItemClickListener?.invoke(elementList[position])

            val scale = holder.view.context.resources.displayMetrics.density

            holder.binding.cardFront.cameraDistance = 8000 * scale
            holder.binding.cardBack.cameraDistance = 8000 * scale

            val front_anim: AnimatorSet = AnimatorInflater.loadAnimator(
                holder.view.context,
                R.animator.front_animator
            ) as AnimatorSet
            val back_anim: AnimatorSet = AnimatorInflater.loadAnimator(
                holder.view.context,
                R.animator.back_animator
            ) as AnimatorSet



            if (elementList[position].isFlipped > 0 && listFlipped.size == 2) {
                front_anim.setTarget(holder.binding.cardBack)
                back_anim.setTarget(holder.binding.cardFront)
                back_anim.start()
                front_anim.start()
            }

            if (elementList[position].isFlipped < 2 && listFlipped.size < 2) {
                if (listFlipped.size == 1 && listFlipped[0] == position) {

                } else {
                    prefs.saveMoves(prefs.getMoves() + 1)



                    front_anim.setTarget(holder.binding.cardFront)
                    back_anim.setTarget(holder.binding.cardBack)
                    front_anim.start()
                    back_anim.start()
                    elementList[position].isFlipped += 1
                    listFlipped.add(position)

                    if (listFlipped.size == 2) {


                        if (elementList[listFlipped[0]].kana == elementList[listFlipped[1]].kana) {
                            numMatched += 2
                            if (numMatched == elementList.size) {
                                MaterialDialog(holder.view.context).show {
                                    title(text = "Congratulations!")
                                    holder.view.findNavController().navigate(R.id.action_gameFragment_to_menuFragment)

                                    message(text = "You have completed the game in ${prefs.getMoves()} moves and ${prefs.getTime()} seconds")
                                }
                            }
                            Toast.makeText(holder.view.context, "Correct", Toast.LENGTH_SHORT)
                                .show()

                            listFlipped.clear()
                        } else {
                            Toast.makeText(holder.view.context, "Incorrect", Toast.LENGTH_SHORT)
                                .show()


                            holder.itemView.postDelayed({
//                            holder.bind(elementList[listFlipped[0]])
                                val rvView = holder.view.parent as RecyclerView
                                rvView.get(listFlipped[0]).performClick()


                                front_anim.setTarget(holder.binding.cardBack)
                                back_anim.setTarget(holder.binding.cardFront)
                                back_anim.start()
                                front_anim.start()
                                elementList[position].isFlipped = 0
                                elementList[listFlipped[0]].isFlipped = 0
                                listFlipped.clear()
                            }, 2000)


                        }

                    }
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return elementList.size
    }
}