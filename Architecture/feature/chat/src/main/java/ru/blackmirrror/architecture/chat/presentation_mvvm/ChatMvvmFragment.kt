package ru.blackmirrror.architecture.chat.presentation_mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentController
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.chat.R
import ru.blackmirrror.architecture.chat.databinding.FragmentChatMvvmBinding

class ChatMvvmFragment : Fragment() {

    private var binding: FragmentChatMvvmBinding ?= null
    private lateinit var viewModel: ChatMvvmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatMvvmBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding?.messageField?.setOnClickListener {
            viewModel.sendMessage(binding?.messageField?.text.toString())
            viewModel.getMessages()
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.messages.collect { state ->
                when (state) {
                    is ResultState.Loading -> TODO()
                    is ResultState.Error -> TODO()
                    is ResultState.Success -> TODO()
                }
            }
        }
    }
}