package ru.batura.stat.batchat.ui.chatlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import ru.batura.stat.batchat.repository.integrator.IIntegrator


class ChatListViewModel @ViewModelInject constructor(private val integrator: IIntegrator): ViewModel() {

    val messagesLive = integrator.getMessages()


}