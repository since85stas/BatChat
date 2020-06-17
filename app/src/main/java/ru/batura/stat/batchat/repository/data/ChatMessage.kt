package ru.batura.stat.batchat.repository.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
data class ChatMessage (
    var name: String? = null,
    var text: String? = null,
    var photoUrl: String? = null
){
    @PrimaryKey(autoGenerate = true)
    var messId: Long = 0
}