package ru.batura.stat.batchat.repository.Firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ru.batura.stat.batchat.repository.data.ChatMessage
import javax.inject.Inject

interface IFiebase

class FirebaseDataSource @Inject constructor() {

//    private val mMessagesDatabaseReference: DatabaseReference? = null

    private val database: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.child("messages")
    }

    fun pushMessage(chatMessage: ChatMessage) {
        database!!.push().setValue(chatMessage)
    }


}