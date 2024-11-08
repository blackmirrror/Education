package ru.blackmirrror.architecture.list_users.presentation_mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.blackmirrror.architecture.api.utils.ResultState
import ru.blackmirrror.architecture.list_users.R
import ru.blackmirrror.architecture.list_users.databinding.FragmentListUsersMvvmBinding


class ListUsersMvvmFragment : Fragment() {

    private var binding: FragmentListUsersMvvmBinding? = null
    private lateinit var viewModel: ListUsersMvvmViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListUsersMvvmBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding?.refreshMvvm?.setOnClickListener {
            viewModel.getUsers()
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.users.collect { resultState ->
                when (resultState) {
                    is ResultState.Success -> TODO()
                    is ResultState.Loading -> TODO()
                    is ResultState.Error -> TODO()
                }
            }
        }
    }

}