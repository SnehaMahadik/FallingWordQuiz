package com.babbel.fallingwords.ui.quiz

import com.babbel.fallingwords.data.local.QuizDataRepository
import com.babbel.fallingwords.getMockWordsList
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
 class QuizDataPresenterTest {

    @Mock
    private lateinit var quizDataRepository: QuizDataRepository
    lateinit var quizDataPresenter: QuizDataPresenter

    @Test
    fun getQuizData() {
        quizDataRepository=Mockito.mock(QuizDataRepository::class.java)
        quizDataPresenter= QuizDataPresenter(quizDataRepository)
        Mockito.`when`(quizDataRepository.getQuizData()).thenReturn(getMockWordsList())
        quizDataPresenter.getQuizData()
        quizDataPresenter.getQuizData()?.let { Assert.assertEquals(it.size, 2) }
    }
}