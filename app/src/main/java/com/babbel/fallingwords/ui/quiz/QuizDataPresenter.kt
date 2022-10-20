package com.babbel.fallingwords.ui.quiz

import com.babbel.fallingwords.data.local.QuizDataRepository
import com.babbel.fallingwords.model.Word

class QuizDataPresenter(_quizDataRepository: QuizDataRepository) : QuizDataMVP.Presenter {

    var quizDataRepository = _quizDataRepository

    override fun getQuizData(): List<Word>? {
        return quizDataRepository.getQuizData()
    }
}