package ru.batura.stat.batchat.repository

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseUser
import ru.batura.stat.batchat.repository.data.ChatMessage

interface IRepository {

    fun sendMessage(chatMessage: ChatMessage)

    fun recieveMessages(): LiveData<List<ChatMessage>>

    fun isLogged(): LiveData<Boolean?>

    fun getCurrentUser(): LiveData<FirebaseUser?>
}