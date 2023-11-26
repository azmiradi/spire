package com.azmiradi.kotlin_base.domain.usecases

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

abstract class UseCaseRemoteThenCache<Domain, in Body> : UseCaseRemote<Domain, Body>() {

    override operator fun invoke(body: Body?): Flow<Resource<Domain>> =
        channelFlow {
            send(Resource.Loading)
            runFlow(executeRemoteDS(body)) {
                send(it)
            }.collect {
                try {
                    if (performLocalOperation(it))
                        executeLocalOperation(it, body)
                    send(invokeSuccessState(it))
                } catch (e: BaseException) {
                    send(invokeFailureState(e))
                }
            }
            close()
        }

    protected abstract suspend fun performLocalOperation(domain: Domain): Boolean

    protected abstract suspend fun executeLocalOperation(domain: Domain, body: Body?)
}