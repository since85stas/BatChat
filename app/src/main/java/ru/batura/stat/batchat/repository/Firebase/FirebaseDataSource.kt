package ru.batura.stat.batchat.repository.Firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import ru.batura.stat.batchat.repository.data.ChatMessage
import javax.inject.Inject
import javax.inject.Singleton

interface IFirebase {

    fun pushMessage(chatMessage: ChatMessage)
    fun getMessage(): LiveData<ChatMessage?>
}

@Singleton
class FirebaseDataSource @Inject constructor() : IFirebase{

//    private val mMessagesDatabaseReference: DatabaseReference? = null

//    private val database: DatabaseReference by lazy {
//        FirebaseDatabase.getInstance().reference.child("messages")
//    }

    private var messageLive: MutableLiveData<ChatMessage?> = MutableLiveData(null)

    private lateinit var dataRef: DatabaseReference

    private var mChildEventListener: ChildEventListener? = null

    init {
        val database = FirebaseDatabase.getInstance()
        dataRef = database.getReference("messages")

        attachDatabaseReadListener()
    }

    /**
     * отправляем сообщение в нынешнюю папку БД
     */
    override fun pushMessage(chatMessage: ChatMessage) {
        dataRef.push().setValue(chatMessage)
    }

    /**
     * посылаем сообщение в активити
     */
    override fun getMessage(): LiveData<ChatMessage?> {
        return messageLive
    }

    private fun attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = object : ChildEventListener {
                override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                    val friendlyMessage: ChatMessage? = dataSnapshot.getValue(
                        ChatMessage::class.java
                    )
//                    mMessageAdapter.add(friendlyMessage)
                    messageLive.postValue(friendlyMessage)
                }

                override fun onChildChanged(
                    dataSnapshot: DataSnapshot,
                    s: String?
                ) {
                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
                override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
                override fun onCancelled(databaseError: DatabaseError) {}
            }
            dataRef.addChildEventListener(mChildEventListener!!)
        }
    }

    private fun detachDatabaseReadListener() {
        if (mChildEventListener != null) {
            dataRef.removeEventListener(mChildEventListener!!)
            mChildEventListener = null
        }
    }
}