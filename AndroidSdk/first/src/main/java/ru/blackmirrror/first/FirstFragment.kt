package ru.blackmirrror.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import ru.blackmirrror.core.Router
import ru.blackmirrror.core.SecondScreen
import ru.blackmirrror.first.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val appRouter: Router by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        setButtons()

        return binding.root
    }

    private fun setButtons() {
        binding.btnNext.setOnClickListener {
            appRouter.navigateTo(SecondScreen)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}