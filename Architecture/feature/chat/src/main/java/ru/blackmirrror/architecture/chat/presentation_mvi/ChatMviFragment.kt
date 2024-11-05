package ru.blackmirrror.architecture.chat.presentation_mvi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.chat.R
import ru.blackmirrror.architecture.chat.databinding.FragmentChatMviBinding

class ChatMviFragment : Fragment() {

    private var binding: FragmentChatMviBinding ?= null
    private lateinit var viewModel: ChatMviViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatMviBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding?.messageField?.setOnClickListener {
            viewModel.onAction(ChatIntent.SendMessage(binding?.messageField?.text.toString()))
            viewModel.onAction(ChatIntent.GetMessages)
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