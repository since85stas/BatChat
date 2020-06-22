package ru.batura.stat.batchat.ui.chat

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.chat_fragment.*
import ru.batura.stat.batchat.R
import ru.batura.stat.batchat.repository.data.ChatMessage

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private val DEFAULT_MSG_LENGTH_LIMIT = 1000

    private val TAG = ChatFragment::class.simpleName

    companion object {
        fun newInstance() = ChatFragment()
    }


    private lateinit var chatViewModel: ChatViewModel

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewManager = LinearLayoutManager(requireContext())

        messageListView.layoutManager = viewManager

        messageEditText.setFilters(arrayOf<InputFilter>(LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)))

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
                if (charSequence.toString().trim { it <= ' ' }.isNotEmpty()) {
                    sendButton.setEnabled(true)
                } else {
                    sendButton.setEnabled(false)
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        sendButton.setOnClickListener {
            chatViewModel.sendMessage(messageEditText.text.toString(),
//                chatViewModel.currentUser.value!!,
                null,
                null)

            messageEditText.setText("")
        }
    }


    /**
     * creating message adapter
     */
    private fun createAdapter() {
        val chatAdapter = ChatAdapter()
        chatAdapter.submitList(messageList)
        messageListView.adapter = chatAdapter
    }

    /**
     * adding observers for view models
     */
    private fun addObservers() {

//         observing incoming messages
//        chatViewModel.messagesFromFB.observe(viewLifecycleOwner, Observer {
//            if (it != null) {
//                messageList.add(it)
//                createAdapter()
//            }
//        })

    }

    override fun onResume() {
        super.onResume()
        addObservers()
    }


}
