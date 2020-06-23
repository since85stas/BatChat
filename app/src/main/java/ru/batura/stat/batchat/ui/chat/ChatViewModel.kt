package ru.batura.stat.batchat.ui.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import ru.batura.stat.batchat.repository.Repository
import ru.batura.stat.batchat.repository.integrator.IIntegrator
import ru.batura.stat.batchat.repository.data.ChatMessage

class ChatViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {

    val currentUser = repository.getCurrentUser()
//
//    val messagesFromFB = integrator.getMessage()
//
    val messagesLive  = repository.recieveMessages()


//    var messagesListLive: MutableLiveData<MutableList<ChatMessage>> = MutableLiveData(messageList)



    init {
        print("intt")
    }

    /**
     *  посылаем сообщение в репозиторий
     */
    fun  sendMessage(text:String, user: FirebaseUser?, url: String? ) {
        val chatMessage = ChatMessage(user!!.displayName!!,
            user.uid,
            text,
            url,
            true,
            false,
            0)

        repository.sendMessage(chatMessage)
    }


}
