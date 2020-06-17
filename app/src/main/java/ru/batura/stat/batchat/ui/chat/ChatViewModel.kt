package ru.batura.stat.batchat.ui.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import ru.batura.stat.batchat.repository.IRepository
import ru.batura.stat.batchat.repository.data.ChatMessage

class ChatViewModel @ViewModelInject constructor(private val repository: IRepository) : ViewModel() {

    val currentUser = repository.getCurrentUser()

    val messagesFromFB = repository.getMessage()

    val mesagesFromDB  = repository.getMessages()


//    var messagesListLive: MutableLiveData<MutableList<ChatMessage>> = MutableLiveData(messageList)

    init {
        print("intt")

    }

    /**
     *  посылаем сообщение в репозиторий
     */
    fun  sendMessage(text:String, user: FirebaseUser, url: String? ) {
        val chatMessage = ChatMessage(user.displayName, text, url)

        repository.pushMessage(chatMessage)

        repository.insertMessage(chatMessage)
    }

    /**
     * фабрика для создания модели
     */
    class Factory(
        private val repository: IRepository
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
                return ChatViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}
