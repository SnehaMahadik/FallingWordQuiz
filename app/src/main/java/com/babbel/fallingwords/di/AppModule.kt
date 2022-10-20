package com.babbel.fallingwords.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @ActivityScoped
    @Provides
    fun provideContext(context: ApplicationContext): ApplicationContext {
        return context
    }
}