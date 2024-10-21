package ru.blackmirrror.dagger2.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.blackmirrror.dagger2.core.di.AppViewModelFactory
import ru.blackmirrror.dagger2.home.di.DaggerHomeComponent
import ru.blackmirrror.dagger2.home.di.HomeComponent
import ru.blackmirrror.dagger2.home.presentation.HomeViewModel
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var appComponent: HomeComponent
    @Inject
    lateinit var factory: AppViewModelFactory
    private val viewModel: HomeViewModel by viewModels {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent = DaggerHomeComponent.builder().build()
        appComponent.injectHomeFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onGetFirstResult()
        viewModel.onGetSecondResult()
    }
}