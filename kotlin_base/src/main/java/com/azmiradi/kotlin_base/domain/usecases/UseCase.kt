package com.azmiradi.kotlin_base.domain.usecases

import com.azmiradi.kotlin_base.data.exception.BaseException
import com.azmiradi.kotlin_base.data.models.Resource
import kotlinx.coroutines.flow.Flow

/**
- Acts as a contract for all the use cases in our application:
 * Abstract class for a Use Case (Interact in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means that any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.

 * @param Domain: the result returned after mapping the response to to the View
 * @param Body: a parameters class which will be consumed inside the [invoke] function
 *                in case we need extra data for our use case.
 */
abstract class UseCase<Domain, in Body> {

    abstract operator fun invoke(
        body: Body? = null,
    ): Flow<Resource<Domain>>

    protected open fun invokeSuccessState(domain: Domain): Resource<Domain> {
        return Resource.Success(domain)
    }

    protected open suspend fun invokeFailureState(exception: BaseException): Resource<Domain> {
        return Resource.Failure(exception)
    }

    protected abstract suspend fun <M> runFlow(
        requestExecution: Flow<M>, onResult: suspend (Resource<Domain>) -> Unit = {}
    ): Flow<M>
}