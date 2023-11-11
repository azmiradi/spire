package com.bumblebeeai.spire.common.di

import com.azmiradi.kotlin_base.domain.usecases.ValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideValidatorUseCase(): ValidatorUseCase {
        return ValidatorUseCase()
    }
}