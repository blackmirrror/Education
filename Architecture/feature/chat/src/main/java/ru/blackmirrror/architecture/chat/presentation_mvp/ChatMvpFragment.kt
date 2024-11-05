package ru.blackmirrror.architecture.chat.presentation_mvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.blackmirrror.architecture.chat.R
import ru.blackmirrror.architecture.chat.databinding.FragmentChatMvpBinding
import ru.blackmirrror.architecture.chat.domain.model.Message

class ChatMvpFragment : Fragment(), ChatContract.View {

    private var binding: FragmentChatMvpBinding ?= null
    private lateinit var presenter: ChatPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatMvpBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getMessages()

        binding?.messageField?.setOnClickListener {
            presenter.sendMessage(binding?.messageField?.text.toString())
            presenter.getMessages()
        }
    }

    override fun showLoadingState() {
        TODO()
    }

    override fun showErrorState() {
        TODO()
    }

    override fun setMessages(messages: List<Message>) {
        TODO()
    }
}