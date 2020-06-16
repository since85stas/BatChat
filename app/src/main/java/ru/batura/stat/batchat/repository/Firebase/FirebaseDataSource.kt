package ru.batura.stat.batchat.repository.Firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ru.batura.stat.batchat.repository.data.ChatMessage
import javax.inject.Inject
import javax.inject.Singleton

interface IFirebase {

    fun pushMessage(chatMessage: ChatMessage)

}

@Singleton
class FirebaseDataSource @Inject constructor() : IFirebase{

//    private val mMessagesDatabaseReference: DatabaseReference? = null

//    private val database: DatabaseReference by lazy {
//        FirebaseDatabase.getInstance().reference.child("messages")
//    }

    private lateinit var dataRef: DatabaseReference

    init {
        val database = FirebaseDatabase.getInstance()
        dataRef = database.getReference("messages")
    }

    /**
     * отправляем сообщение в нынешнюю папку БД
     */
    override fun pushMessage(chatMessage: ChatMessage) {
        dataRef!!.push().setValue(chatMessage)
    }


}