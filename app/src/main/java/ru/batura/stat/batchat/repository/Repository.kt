package ru.batura.stat.batchat.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.batura.stat.batchat.repository.data.ChatMessage
import ru.batura.stat.batchat.repository.integrator.Integrator
import javax.inject.Inject

class Repository @Inject constructor() : IRepository {

    @Inject lateinit var  integrator: Integrator

    override fun sendMessage(chatMessage: ChatMessage) {
        val res = runBlocking {
            integrator.insertMessage(chatMessage)
        }
        chatMessage.messId = res
        integrator.pushMessage(chatMessage)
        print("end")
    }

    override fun recieveMessage(): LiveData<ChatMessage> {
        TODO("Not yet implemented")
    }

    override fun isLogged(): LiveData<Boolean?> {
        return integrator.isLogged()
    }
}