package ru.batura.stat.batchat.ui.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import ru.batura.stat.batchat.repository.Repository
import ru.batura.stat.batchat.repository.integrator.IIntegrator
import ru.batura.stat.batchat.repository.data.ChatMessage

class ChatViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {

//    val currentUser = integrator.getCurrentUser()
//
//    val messagesFromFB = integrator.getMessage()
//
//    val mesagesFromDB  = integrator.getMessages()


//    var messagesListLive: MutableLiveData<MutableList<ChatMessage>> = MutableLiveData(messageList)

    init {
        print("intt")

    }

    /**
     *  посылаем сообщение в репозиторий
     */
    fun  sendMessage(text:String, user: FirebaseUser?, url: String? ) {
        val chatMessage = ChatMessage(null,
            text,
            url,
            true,
            false,
            0)

        repository
            .sendMessage(chatMessage)
//
//        integrator.insertMessage(chatMessage)
    }

    /**
     * фабрика для создания модели
     */
//    class Factory(
//        private val integrator: IIntegrator
//    ) : ViewModelProvider.Factory {
//        @Suppress("unchecked_cast")
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
//                return ChatViewModel(integrator) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }

}
