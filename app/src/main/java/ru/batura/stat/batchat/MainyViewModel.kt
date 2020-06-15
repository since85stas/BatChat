package ru.batura.stat.batchat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.batura.stat.batchat.repository.IRepository

class MainViewModel @ViewModelInject constructor(private val repository: IRepository): ViewModel() {

    val isLogging = repository.isLogged()

    /**
     * фабрика для создания модели
     */
    class Factory(
        private val repository: IRepository
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}