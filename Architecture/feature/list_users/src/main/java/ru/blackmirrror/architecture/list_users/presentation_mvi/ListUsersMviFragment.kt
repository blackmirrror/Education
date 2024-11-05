package ru.blackmirrror.architecture.list_users.presentation_mvi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.list_users.databinding.FragmentListUsersMviBinding


class ListUsersMviFragment : Fragment() {

    private var binding: FragmentListUsersMviBinding? = null
    private lateinit var viewModel: ListUsersMviViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListUsersMviBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()

        binding?.refreshMvi?.setOnClickListener {
            viewModel.onAction(ListUsersIntent.GetUsers)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.users.collect { users ->
                when (users) {
                    is ResultState.Success -> TODO()
                    is ResultState.Loading -> TODO()
                    is ResultState.Error -> TODO()
                }
            }
        }
    }
}