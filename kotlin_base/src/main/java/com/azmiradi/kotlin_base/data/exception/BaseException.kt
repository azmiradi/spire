package com.azmiradi.kotlin_base.data.exception

import com.azmiradi.kotlin_base.utilities.extensions.getModelFromJSON
import java.lang.reflect.Type
import kotlin.reflect.KClass

sealed class BaseException(message: String?) : Exception(message) {
    sealed class Network(message: String? = null) : BaseException(message) {
        data class Retrial(val messageRes: Int, override val message: String?) :
            Network(message)

        data class Unhandled(val messageRes: Int, override val message: String?) :
            Network(message)
    }

    sealed class Client(message: String? = null) : BaseException(message) {
        data object Unauthorized : Client(message = "Unauthorized Access.")

        data class ResponseValidation(
            val errors: Map<String, String> = hashMapOf(), override val message: String?,
        ) : BaseException(message)

        data class Unhandled(val httpErrorCode: Int, override val message: String?) : Client(
            message = "Unhandled client error with code:${httpErrorCode}, and the failure reason: $message"
        )

       data class BodyError(val httpErrorCode: Int, override val message: String?) : Client(
            message = message
        ) {
            fun <ErrorBody> getErrorBody(type: Type): ErrorBody? {
                return message?.getModelFromJSON<ErrorBody>(type)
            }
        }
    }

    sealed class Server(message: String? = null) : BaseException(message) {
        data class InternalServerError(val httpErrorCode: Int, override val message: String?) :
            Server(message = "Internal server error with code:${httpErrorCode}, and the failure reason: $message")
    }

    sealed class Local(message: String? = null) : BaseException(message) {
        data class RequestValidation(
            val clazz: KClass<*>,
            val messageRes: Int,
            override val message: String? = null,
        ) :
            Local(StringBuilder("There is missing input for this class: ${clazz.simpleName}").apply {
                message?.let { append(", message: $message") }
            }.toString())

        data class IOOperation(val messageRes: Int, override val message: String? = "") :
            Local(message)

        data class NotFoundData(val messageRes: Int, override val message: String? = "") :
            Local(message)
    }

    data class Unknown(override val message: String?) : BaseException(message)
}