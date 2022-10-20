package com.babbel.fallingwords.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils.loadAnimation
import androidx.fragment.app.Fragment
import com.babbel.fallingwords.R
import com.babbel.fallingwords.databinding.FragmentFirstBinding
import com.babbel.fallingwords.model.Word
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class QuizFragment : Fragment(), QuizDataMVP.View {

    @Inject
    lateinit var presenter: QuizDataMVP.Presenter

    private var _binding: FragmentFirstBinding? = null
    private var score = 0
    private var numberOfQuestions = 5
    var counter = 0
    private val binding get() = _binding!!
    var randomWord: Word? = null
    private var wordsDataList: List<Word>? = null
    private lateinit var  animationFalling : Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animationFalling = loadAnimation(context, R.anim.falling)
        wordsDataList = presenter.getQuizData()

        setupAnimationListener()
        binding.buttonStartQuiz.setOnClickListener {
            startQuiz()
        }
    }

    private fun startQuiz() {
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

    private fun setupAnimationListener() {
        animationFalling.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                val randomIndex =
                    wordsDataList?.size?.let { Random(System.currentTimeMillis()).nextInt(1, it) }
                randomWord = randomIndex?.let { wordsDataList?.get(it) }
                setupViewsOnStartQuiz(randomWord)
                counter += 1
            }

            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                setupViewsOnEndQuiz()
                binding.apply {
                    if (counter <= numberOfQuestions) {
                        buttonStartQuiz.text = getString(R.string.button_text_next)
                    }
                    if (counter == numberOfQuestions) {
                        buttonStartQuiz.text = getString(R.string.get_my_score)
                    }
                    if (buttonStartQuiz.text == getString(R.string.get_my_score)) {
                        group1.visibility = INVISIBLE
                    }
                }
            }
        })
    }

    override fun setupViewsOnEndQuiz() {
        binding.apply {
            textviewAnswer.visibility = INVISIBLE
            textviewQuestion.visibility = INVISIBLE
            buttonStartQuiz.isEnabled = true
            buttonCorrect.isEnabled = false
            buttonWrong.isEnabled = false
        }

    }

    override fun setupViewsOnStartQuiz(randomWord: Word?) {
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