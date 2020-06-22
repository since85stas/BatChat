package ru.batura.stat.batchat.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * storing messages in existing chats
 */
@Entity(tableName = "message_table")
data class ChatMessage (
    var name: String? = null,

    var text: String? = null,

    var photoUrl: String? = null,

    var isSend: Boolean = false,

    var isReceive: Boolean = false,

    var chatId: Long = 0L,

    @PrimaryKey(autoGenerate = true)
    var messId: Long = 0

){

}