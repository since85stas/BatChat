package ru.batura.stat.batchat.repository

import androidx.lifecycle.LiveData
import ru.batura.stat.batchat.repository.data.ChatMessage

interface IRepository {

    fun sendMessage(chatMessage: ChatMessage)

    fun recieveMessage(): LiveData<ChatMessage>

    fun isLogged(): LiveData<Boolean?>
}