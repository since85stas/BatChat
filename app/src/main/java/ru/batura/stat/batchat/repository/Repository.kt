package ru.batura.stat.batchat.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.batura.stat.batchat.repository.data.ChatMessage
import ru.batura.stat.batchat.repository.integrator.Integrator
import javax.inject.Inject

class Repository @Inject constructor() : IRepository {

    @Inject lateinit var  integrator: Integrator

    private var messages : MutableLiveData<List<ChatMessage>> = MutableLiveData()

    /**
     * sending a message, saving in DB and pushing to FB
     */
    override fun sendMessage(chatMessage: ChatMessage) {
        val res = runBlocking {
            integrator.insertMessage(chatMessage)
        }
        chatMessage.messId = res
        integrator.pushMessage(chatMessage)
        print("end")
    }

    /**
     * recieving incoming message
     */
    override fun recieveMessages(): LiveData<List<ChatMessage>> {
        return integrator.getMessages()
    }

    /**
     * check is user is logged in
     */
    override fun isLogged(): LiveData<Boolean?> {
        return integrator.isLogged()
    }

    override fun getCurrentUser(): LiveData<FirebaseUser?> {
        return integrator.getCurrentUser()
    }
}