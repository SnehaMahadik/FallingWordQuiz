package com.babbel.fallingwords.data.local

import android.content.Context
import com.babbel.fallingwords.Utils
import com.babbel.fallingwords.model.Word

class QuizDataRepositoryImpl(_context: Context) : QuizDataRepository {

    var context = _context

    override fun getQuizData(): List<Word>? {
        return context.let { Utils.getWordList(it, "words.json") }
    }
}