package ru.batura.stat.batchat.ui.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseUser
import ru.batura.stat.batchat.repository.IRepository

class ChatViewModel @ViewModelInject constructor(private val repository: IRepository) : ViewModel() {

    val currentUser = repository.getCurrentUser()


    init {
        print("intt")

    }

    fun  sendMessage(text:String, user: FirebaseUser, url: String? ) {
        repository.
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
