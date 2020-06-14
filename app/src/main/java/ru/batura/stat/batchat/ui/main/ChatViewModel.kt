package ru.batura.stat.batchat.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.batura.stat.batchat.repository.Repository
import javax.inject.Inject

@AndroidEntryPoint
class ChatViewModel : ViewModel() {

    @Inject lateinit var repository:  Repository

}
