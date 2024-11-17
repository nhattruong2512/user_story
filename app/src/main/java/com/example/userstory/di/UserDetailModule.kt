package com.example.userstory.di

import com.example.userstory.domain.usecase.UserDetailUseCase
import com.example.userstory.domain.usecase.impl.UserDetailUseCaseImpl
import com.example.userstory.presentation.presenter.UserDetailPresenter
import com.example.userstory.presentation.presenter.impl.UserDetailPresenterImpl
import com.example.userstory.presentation.ui.activity.UserDetailActivity
import com.example.userstory.presentation.ui.view.UserDetailView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UserDetailModule {
    @Provides
    fun provideUserDetailView(activity: UserDetailActivity): UserDetailView = activity

    @Provides
    fun provideUserDetailUseCase(impl: UserDetailUseCaseImpl): UserDetailUseCase = impl

    @Provides
    fun provideUserDetailPresenter(impl: UserDetailPresenterImpl): UserDetailPresenter = impl
}