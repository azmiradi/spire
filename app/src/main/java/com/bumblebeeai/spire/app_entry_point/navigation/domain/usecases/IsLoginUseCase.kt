package com.bumblebeeai.spire.app_entry_point.navigation.domain.usecases

import com.azmiradi.kotlin_base.domain.usecases.UseCaseLocal
import com.bumblebeeai.spire.app_entry_point.navigation.domain.repository.INavigationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class IsLoginUseCase @Inject constructor(private val repository: INavigationRepository) :
    UseCaseLocal<Boolean, Any>() {
    override fun executeLocalDS(body: Any?): Flow<Boolean> = flow {
        emit(repository.isLoginUser())
    }
}