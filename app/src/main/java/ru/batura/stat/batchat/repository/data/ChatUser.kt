package ru.batura.stat.batchat.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "chats_users_table")
data class ChatUser (

    var authorKey: String? = null,

    var authorName: String? = null,

    @PrimaryKey(autoGenerate = true)
    var chatId: Long = 1L

) {

}




