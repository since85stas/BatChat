package ru.batura.stat.batchat.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.batura.stat.batchat.repository.Firebase.FirebaseAuthSource
import ru.batura.stat.batchat.repository.Firebase.FirebaseDataSource
import ru.batura.stat.batchat.repository.data.Chat
import ru.batura.stat.batchat.repository.data.ChatMessage
import ru.batura.stat.batchat.repository.data.ChatUser
import ru.batura.stat.batchat.repository.room.ChatDao
import javax.inject.Inject


class Repository @Inject constructor() : IRepository {

    /**
     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
     */
    private var repositoryJob = Job()

    /**
     * A [CoroutineScope] keeps track of all coroutines started by this ViewModel.
     *
     * Because we pass it [repositoryJob], any coroutine started in this uiScope can be cancelled
     * by calling `viewModelJob.cancel()`
     *
     * By default, all coroutines started in uiScope will launch in [Dispatchers.Main] which is
     * the main thread on Android. This is a sensible default because most coroutines started by
     * a [ViewModel] update the UI after performing some processing.
     */
    private val ioScope = CoroutineScope(Dispatchers.IO + repositoryJob)

    @Inject lateinit var firebaseAuthSource: FirebaseAuthSource

    @Inject lateinit var firebaseDataSource: FirebaseDataSource

    @Inject lateinit var roomDataSource: ChatDao

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

    //-----------------------------MESSAGE PART FIRE------------------------------------------------
    override fun pushMessage(chatMessage: ChatMessage) {
       firebaseDataSource.pushMessage(chatMessage)
    }

    override fun getMessage(): LiveData<ChatMessage?> {
        return firebaseDataSource.getMessage()
    }

    //---------------------------MESSAGE ROOM PART--------------------------------------------------
    override fun insertMessage(message: ChatMessage) {
        ioScope.launch {
            roomDataSource.insertMessage(message)
        }
    }

    override fun getMessages(): LiveData<List<ChatMessage>> {
        return roomDataSource.getMessages()
    }
    
    //--------------------------CHAT USERS PART----------------------------------------------------
    override fun insertChatUser(chatUser: ChatUser) {
        ioScope.launch { 
            roomDataSource.insertChatUser(chatUser)
        }
    }

    override fun getChatUsers(chatId: Long): LiveData<List<ChatUser>> {
        return roomDataSource.getChatUsers(chatId)
    }

    //--------------------------CHAT PART---------------------------------------------------------
    override fun insertChat(chat: Chat) {
        ioScope.launch { 
            roomDataSource.insertChat(chat)
        }
    }

    override fun getChats(): LiveData<Chat> {
        return roomDataSource.getChats()
    }
    
}