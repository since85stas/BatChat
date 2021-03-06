package ru.batura.stat.batchat.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.batura.stat.batchat.repository.data.Chat
import ru.batura.stat.batchat.repository.data.ChatMessage
import ru.batura.stat.batchat.repository.data.ChatUser

@Dao
interface ChatDao {

    @Insert
    suspend fun insertMessage(message: ChatMessage): Long

    @Query("SELECT * FROM message_table ")
    fun getMessages(): LiveData<List<ChatMessage>>

    @Insert
    fun insertChatUser(chatUser: ChatUser)

    @Query("SELECT * FROM chats_users_table WHERE chatId = :chatId")
    fun getChatUsers(chatId: Long): LiveData<List<ChatUser>>

    @Insert()
    fun insertChat(chat: Chat)

    @Query("SELECT * FROM chat_table")
    fun getChats(): LiveData<Chat>



}