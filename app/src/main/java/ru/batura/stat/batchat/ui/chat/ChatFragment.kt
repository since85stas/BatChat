package ru.batura.stat.batchat.ui.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.chat_fragment.*
import ru.batura.stat.batchat.R
import ru.batura.stat.batchat.repository.Firebase.FirebaseDataSource
import ru.batura.stat.batchat.repository.data.ChatMessage

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private val TAG = ChatFragment::class.simpleName

    companion object {
        fun newInstance() = ChatFragment()
    }


    private lateinit var chatViewModel: ChatViewModel

    private lateinit var chatAdapter: MessageAdapter

    private var messageList: MutableList<ChatMessage> = mutableListOf()

    /**
     * создаем вью
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // получаем модель
        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        return inflater.inflate(R.layout.chat_fragment, container, false)
    }

    /**
     * после создания вью, добавляем слушатели
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val database = FirebaseDatabase.getInstance()
//        dataRef = database.getReference("messages")
//        val list = ArrayList<ChatMessage>()
//        chatAdapter = MessageAdapter(requireContext(), R.layout.item_message, list)
        val viewManager = LinearLayoutManager(requireContext())

        messageListView.layoutManager = viewManager

//        createAdapter()
//        messageListView.adapter = chatAdapter



        // Enable Send button when there's text to send
        messageEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                i: Int,
                i1: Int,
                i2: Int
            ) {
                if (charSequence.toString().trim { it <= ' ' }.length > 0) {
                    sendButton.setEnabled(true)
                } else {
                    sendButton.setEnabled(false)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        sendButton.setOnClickListener {
            chatViewModel.sendMessage(messageEditText.text.toString(), chatViewModel.currentUser.value!!, null)
        }
    }

    private fun createAdapter() {
        val chatAdapter = ChatAdapter()
        chatAdapter.submitList(messageList)
        messageListView.adapter = chatAdapter
    }

    private fun addObservers() {

        // observing incoming messages
        chatViewModel.messagesFromFB.observe(viewLifecycleOwner, Observer {
            if (it != null) {
//                chatAdapter.add(it)
                messageList.add(it)
                createAdapter()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        addObservers()
    }


}
