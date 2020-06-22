package ru.batura.stat.batchat.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * storing existing chats list
 */
@Entity(tableName = "chat_table")
data class Chat(
    var chatName: String = "chat"
) {
    @PrimaryKey(autoGenerate = true)
    var chatId: Long = 0
}