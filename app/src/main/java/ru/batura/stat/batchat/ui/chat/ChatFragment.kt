package ru.batura.stat.batchat.ui.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.chat_fragment.*
import ru.batura.stat.batchat.R

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private val TAG = ChatFragment::class.simpleName

    companion object {
        fun newInstance() = ChatFragment()
    }

    private lateinit var chatViewModel: ChatViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.chat_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

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
    }

    private fun addObservers() {

    }

//    override fun onResume() {
//        super.onResume()
////        addObservers()
//    }


}
