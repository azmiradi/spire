package com.azmiradi.kotlin_base.domain.usecases

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn

abstract class UseCaseRemote<Domain, in Body> : UseCase<Domain, Body>() {

    protected abstract fun executeRemoteDS(body: Body? = null): Flow<Domain>

    override operator fun invoke(body: Body?): Flow<Resource<Domain>> =
        channelFlow {
            send(Resource.Loading)
            runFlow(executeRemoteDS(body)) {
                send(it)
            }.collect {
                send(invokeSuccessState(it))
            }
            close()
        }

    override suspend fun <M> runFlow(
        requestExecution: Flow<M>, onResult: suspend (Resource<Domain>) -> Unit
    ): Flow<M> = requestExecution
        .catch { e ->
        val failureResource = if (e is BaseException) e
        else BaseException.Unknown(message = "Unknown error in UseCaseRemote: $e")
        onResult.invoke(invokeFailureState(failureResource))
    }.flowOn(Dispatchers.IO)
}