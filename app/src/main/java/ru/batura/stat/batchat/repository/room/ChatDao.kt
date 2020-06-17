package ru.batura.stat.batchat.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.batura.stat.batchat.repository.data.ChatMessage

@Dao
interface ChatDao {

    @Insert
    fun insertMessage(message: ChatMessage)

    @Query("SELECT * FROM message_table ")
    fun getMessages(): LiveData<List<ChatMessage>>

}