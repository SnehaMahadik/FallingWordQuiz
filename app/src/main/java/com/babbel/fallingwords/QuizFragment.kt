package com.babbel.fallingwords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import androidx.fragment.app.Fragment
import com.babbel.fallingwords.data.Word
import com.babbel.fallingwords.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class QuizFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var score = 0
    var counter = 0
    private val binding get() = _binding!!
     var randomWord : Word? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animationFalling =
            android.view.animation.AnimationUtils.loadAnimation(context, R.anim.falling)

        animationFalling.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                randomWord = context?.let { Utils.getRandomWord(it, "words.json") }
                setupViewsOnStartQuiz(randomWord)
                counter += 1
            }

            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                setupViewsOnEndQuiz()
                binding.apply {
                    if (counter <= 5) {
                        buttonStartQuiz.text = getString(R.string.button_text_next)
                    }
                    if (counter == 5) {
                        buttonStartQuiz.text = getString(R.string.get_my_score)
                    }
                    if (buttonStartQuiz.text == getString(R.string.get_my_score)) {
                        group1.visibility = INVISIBLE
                    }
                }
            }
        })

        binding.buttonStartQuiz.setOnClickListener {
            // start animation
            binding.apply {
                buttonCorrect.isClickable = true
                if (buttonStartQuiz.text != getString(R.string.get_my_score)) {
                    textviewAnswer.startAnimation(animationFalling)
                } else {
                    textviewScore.visibility = VISIBLE
                    textviewScore.text =
                        String.format(resources.getString(R.string.your_score_is), score)
                    buttonStartQuiz.visibility = GONE
                }
                buttonCorrect.setOnClickListener {
                    it.isClickable = false
                    score += 1
                }
            }
        }
    }

    private fun setupViewsOnEndQuiz() {
        binding.apply {
            textviewAnswer.visibility = INVISIBLE
            textviewQuestion.visibility = INVISIBLE
            buttonStartQuiz.isEnabled = true
            buttonCorrect.isEnabled = false
            buttonWrong.isEnabled = false
        }

    }

    private fun setupViewsOnStartQuiz(randomWord: Word?) {
        binding.apply {
            group1.visibility = VISIBLE
            textviewQuestion.text = randomWord?.text_eng
            textviewAnswer.text = randomWord?.text_spa

            buttonStartQuiz.isEnabled = false
            buttonCorrect.isEnabled = true
            buttonWrong.isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}