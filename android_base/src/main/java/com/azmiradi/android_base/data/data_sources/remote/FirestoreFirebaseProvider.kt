package com.azmiradi.android_base.data.data_sources.remote

import com.azmiradi.kotlin_base.domain.repository.remote.FirebaseProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirestoreFirebaseProvider : FirebaseProvider {
    override suspend fun <Model> getListOfObjects(
        collectionName: String,
        clazz: Class<Model>,
    ): List<Model> {
        val itemsList = mutableListOf<Model>()
        val collectionRef = Firebase.firestore.collection(collectionName).get().await()

        for (snap in collectionRef.documents) {
            snap.toObject(clazz)?.let { itemsList.add(it) }
        }
        return itemsList
    }

    override suspend fun <Model> saveObject(
        collectionName: String,
        id: String,
        model: Model,
    ): Boolean {
        Firebase.firestore.collection(collectionName).document(id).set(model!!)
            .await()
        return true
    }

    override suspend fun removeElement(documentPath: String, id: String): Boolean {
        return Firebase.firestore.collection(documentPath).document(id).delete().isSuccessful
    }

    override suspend fun generateUniqueID(collectionName: String): String {
        val numericId = (10000..9999999).random().toString()
        val documentReference =
            Firebase.firestore.collection(collectionName).document(numericId)

        val documentSnapshot = documentReference.get().await()

        return if (documentSnapshot.exists()) {
            generateUniqueID(collectionName)
        } else {
            numericId
        }
    }
}