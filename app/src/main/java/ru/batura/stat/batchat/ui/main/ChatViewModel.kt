package ru.batura.stat.batchat.ui.main

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import ru.batura.stat.batchat.repository.IRepository
import ru.batura.stat.batchat.repository.Repository
import javax.inject.Inject

class ChatViewModel @ViewModelInject constructor(private val repository: IRepository) : ViewModel() {

    val isLogged = repository.isLogged()

    init {
        print("intt")

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
