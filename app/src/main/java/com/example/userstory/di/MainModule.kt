package com.example.userstory.di

import com.example.userstory.domain.usecase.MainUseCase
import com.example.userstory.domain.usecase.impl.MainUseCaseImpl
import com.example.userstory.presentation.presenter.MainPresenter
import com.example.userstory.presentation.presenter.impl.MainPresenterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun provideMainUseCase(impl: MainUseCaseImpl): MainUseCase = impl

    @Provides
    fun provideMainPresenter(impl: MainPresenterImpl): MainPresenter = impl

}