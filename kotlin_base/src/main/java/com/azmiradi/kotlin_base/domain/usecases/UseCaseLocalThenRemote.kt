package com.azmiradi.kotlin_base.domain.usecases

import com.azmiradi.kotlin_base.data.models.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch

abstract class UseCaseLocalThenRemote<Domain, in Body> : UseCaseRemote<Domain, Body>() {

    protected abstract fun executeLocalDS(body: Body?): Flow<Domain>

    protected abstract fun performRemoteOperation(domain: Domain?): Boolean

    override operator fun invoke(
        scope: CoroutineScope, body: Body?, multipleInvoke: Boolean,
        onResult: (Resource<Domain>) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            if (multipleInvoke.not())
                onResult.invoke(Resource.loading())

            // Run local first
            runFlow(executeLocalDS(body), onResult).collect { localData ->
                if (performRemoteOperation(localData)) { // call network and get result
                    runFlow(executeRemoteDS(body), onResult).collect {
                        onResult.invoke(invokeSuccessState(it))

                        if (multipleInvoke.not())
                            onResult.invoke(Resource.loading(false))
                    }
                } else { // get local
                    onResult.invoke(invokeSuccessState(localData))

                    if (multipleInvoke.not())
                        onResult.invoke(Resource.loading(false))
                }
            }
        }
    }

    override operator fun invoke(body: Body?, multipleInvoke: Boolean): Flow<Resource<Domain>> =
        channelFlow {
            if (multipleInvoke.not())
                send(Resource.loading())

            // Run local first
            runFlow(executeLocalDS(body)) {
                send(it)
            }.collect { localData ->
                if (performRemoteOperation(localData)) { // call network and get result
                    runFlow(executeRemoteDS(body)) {
                        send(it)
                    }.collect {
                        send(invokeSuccessState(it))

                        if (multipleInvoke.not())
                            send(Resource.loading(false))
                    }
                } else { // get local
                    send(invokeSuccessState(localData))

                    if (multipleInvoke.not())
                        send(Resource.loading(false))
                }
            }
        }
}