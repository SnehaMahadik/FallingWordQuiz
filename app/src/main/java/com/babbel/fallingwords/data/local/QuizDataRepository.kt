package com.babbel.fallingwords.data.local

import com.babbel.fallingwords.model.Word

interface QuizDataRepository {

    fun getQuizData(): List<Word>?
}