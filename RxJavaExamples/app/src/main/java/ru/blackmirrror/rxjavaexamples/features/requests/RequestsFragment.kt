package ru.blackmirrror.rxjavaexamples.features.requests

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.rxjavaexamples.R
import ru.blackmirrror.rxjavaexamples.databinding.FragmentRequestsBinding
import ru.blackmirrror.rxjavaexamples.features.search.MoviesAdapter

class RequestsFragment : Fragment() {

    private lateinit var binding: FragmentRequestsBinding
    private val viewModel by viewModel<RequestsViewModel>()

    private lateinit var discountCardAdapter: DiscountCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRequestsBinding.inflate(inflater, container, false)

        initRecycler()
        initShowButton()
        observeData()

        return binding.root
    }

    private fun initRecycler() {
        discountCardAdapter = DiscountCardAdapter()
        binding.rvDiscountCards.adapter = discountCardAdapter
    }

    private fun initShowButton() {
        binding.btnShow.setOnClickListener {
            viewModel.changeClick()
        }
    }

    @SuppressLint("CheckResult")
    private fun observeData() {
        viewModel.discountCards
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cards ->
                discountCardAdapter.submitList(cards)
            }

        viewModel.clicked
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { show ->
                if (show)
                    binding.btnShow.text = getString(R.string.show)
                else
                    binding.btnShow.text = getString(R.string.hide)
            }
    }
}