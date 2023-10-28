package com.azmiradi.kotlin_base.domain.usecases

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

abstract class UseCaseRemote<Domain, in Body> : UseCase<Domain, Body>() {

    protected abstract fun executeRemoteDS(body: Body? = null): Flow<Domain>

    override operator fun invoke(
        scope: CoroutineScope, body: Body?, multipleInvoke: Boolean,
        onResult: (Resource<Domain>) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            if (multipleInvoke.not()) onResult.invoke(Resource.loading())

            runFlow(executeRemoteDS(body), onResult).collect {
                onResult.invoke(invokeSuccessState(it))

                if (multipleInvoke.not()) onResult.invoke(Resource.loading(false))
            }
        }
    }

    override operator fun invoke(body: Body?, multipleInvoke: Boolean): Flow<Resource<Domain>> =
        channelFlow {
            if (multipleInvoke.not()) send(Resource.loading())

            runFlow(executeRemoteDS(body)) {
                send(it)
            }.collect {
                send(invokeSuccessState(it))
                if (multipleInvoke.not()) send(Resource.loading(false))
            }
        }

    override fun validateResponseModel(domain: Domain): Resource<Domain>? = null

    override suspend fun validateFailureResponse(exception: BaseException): Resource<Domain>? =
        null

    override suspend fun <M> runFlow(
        requestExecution: Flow<M>, onResult: suspend (Resource<Domain>) -> Unit
    ): Flow<M> = requestExecution.catch { e ->
        val failureResource = if (e is BaseException) e
        else BaseException.Unknown(message = "Unknown error in UseCaseRemote: $e")

        onResult.invoke(invokeFailureState(failureResource))
        onResult.invoke(Resource.loading(false))
    }.flowOn(Dispatchers.IO)
}