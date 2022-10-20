package com.babbel.fallingwords.ui.quiz

import com.babbel.fallingwords.model.Word

interface QuizDataMVP {

    interface View {
        fun setupViewsOnEndQuiz()
        fun setupViewsOnStartQuiz(randomWord: Word?)
    }

    interface Presenter {
        fun getQuizData(): List<Word>?
    }

}