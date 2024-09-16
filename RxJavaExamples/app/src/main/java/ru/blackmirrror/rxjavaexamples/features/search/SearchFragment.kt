package ru.blackmirrror.rxjavaexamples.features.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.rxjavaexamples.R
import ru.blackmirrror.rxjavaexamples.databinding.FragmentSearchBinding
import ru.blackmirrror.rxjavaexamples.features.requests.RequestsFragment

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModel<SearchViewModel>()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        initRecycler()
        initEditText()
        initNextFragment()
        observeMovies()

        return binding.root
    }

    private fun initRecycler() {
        moviesAdapter = MoviesAdapter()
        binding.rvMovies.adapter = moviesAdapter
        moviesAdapter.onMovieItemClickListener = { position ->
            viewModel.clickedMovie.onNext(position)
        }
    }

    private fun initEditText() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onSearchQueryChanged(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    @SuppressLint("CheckResult")
    private fun observeMovies() {
        viewModel.movies
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movies ->
                moviesAdapter.submitList(movies)
            }

        viewModel.clickedMovie
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { position ->
                showToast(position)
            }

        viewModel.time
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { time ->
                binding.tvTimer.text = time
            }
    }

    private fun showToast(position: Int) {
        Toast.makeText(requireContext(), "Position: $position", Toast.LENGTH_SHORT).show()
    }

    private fun initNextFragment() {
        binding.tvNext.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, RequestsFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}