package com.babbel.fallingwords.di

import android.content.Context
import com.babbel.fallingwords.ui.quiz.QuizDataMVP
import com.babbel.fallingwords.data.local.QuizDataRepositoryImpl
import com.babbel.fallingwords.ui.quiz.QuizDataPresenter
import com.babbel.fallingwords.ui.quiz.QuizFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class QuizDataModule {

    @FragmentScoped
    @Provides
    fun bindFragment(quizFragment: QuizFragment): QuizFragment {
        return quizFragment
    }

    @FragmentScoped
    @Provides
    fun provideQuizDataRepository(@ActivityContext context: Context): QuizDataRepositoryImpl {
        return QuizDataRepositoryImpl(context)
    }

    @FragmentScoped
    @Provides
    fun providePresenter(quizDataRepositoryImpl: QuizDataRepositoryImpl): QuizDataMVP.Presenter {
        return QuizDataPresenter(quizDataRepositoryImpl)
    }
}