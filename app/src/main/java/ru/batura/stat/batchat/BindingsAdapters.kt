package ru.batura.stat.batchat

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.batura.stat.batchat.repository.data.ChatMessage

class BindingsAdapters {

    @BindingAdapter("messageTextBind")
    fun TextView.messageTextBind (message: ChatMessage) {
        text = message.text
    }

    @BindingAdapter("messageNameBind")
    fun TextView.messageNameBind (message: ChatMessage) {
        text = message.name
    }

    @BindingAdapter("messageImageBind")
    fun ImageView.messageImageBind (message: ChatMessage) {
        if (message.photoUrl != null) {

        }
    }
}