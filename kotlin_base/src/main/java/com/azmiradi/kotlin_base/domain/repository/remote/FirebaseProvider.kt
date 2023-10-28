package com.azmiradi.kotlin_base.domain.repository.remote

interface FirebaseProvider {
    suspend fun <Model> getListOfObjects(
        collectionName: String,
        clazz: Class<Model>,
    ): List<Model>

    suspend fun <Model> saveObject(collectionName: String, id: String, model: Model): Boolean

    suspend fun removeElement(documentPath: String,id:String):Boolean

    suspend fun generateUniqueID(collectionName: String): String
}