package ru.batura.stat.batchat.ui.chatlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import ru.batura.stat.batchat.repository.IRepository


class ChatListViewModel @ViewModelInject constructor(private val repository: IRepository): ViewModel() {

    val messagesLive = repository.getMessages()


}