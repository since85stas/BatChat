package ru.batura.stat.batchat.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser
import ru.batura.stat.batchat.repository.Firebase.FirebaseAuthSource
import ru.batura.stat.batchat.repository.Firebase.FirebaseDataSource
import ru.batura.stat.batchat.repository.data.ChatMessage
import javax.inject.Inject


class Repository @Inject constructor() : IRepository {

    @Inject lateinit var firebaseAuthSource: FirebaseAuthSource
    @Inject lateinit var firebaseDataSource: FirebaseDataSource

    //---------------------------AUTH PART----------------------------------------------------------
    override fun isLogged(): LiveData<Boolean?> {
        return firebaseAuthSource.isLogged()
    }

    override fun addListner() {
        return firebaseAuthSource.addListner()
    }

    override fun removeListner() {
        return firebaseAuthSource.removeListner()
    }

    override fun getCurrentUser(): LiveData<FirebaseUser?> {
        return firebaseAuthSource.getCurrentUser()
    }

    //-----------------------------MESSAGE PART-----------------------------------------------------
    override fun pushMessage(chatMessage: ChatMessage) {
       firebaseDataSource.pushMessage(chatMessage)
    }

    override fun getMessage(): LiveData<ChatMessage?> {
        return firebaseDataSource.getMessage()
    }
}