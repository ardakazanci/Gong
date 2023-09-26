package com.ardakazanci.gong.core.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.ardakazanci.gong.core.navigation.NavigationFlow
import com.ardakazanci.gong.core.navigation.ToNavigate
import kotlinx.coroutines.launch

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class MotherFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment() {

    private val progress by lazy {
        ProgressUtils(requireActivity())
    }

    private lateinit var flowNavigator: ToNavigate
    private lateinit var viewModel: MotherViewModel
    protected abstract fun mViewModel(): MotherViewModel

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = mViewModel()
        setupLoading()
        setupNavigator()
        setupView()
    }

    private fun setupNavigator() {
        flowNavigator = requireActivity() as ToNavigate
    }

    private fun setupLoading() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.loading.collect {
                        if (it) {
                            progress.showProgress()
                        } else {
                            progress.hideProgress()
                        }
                    }
                }
                launch {
                    viewModel.error.collect {
                        Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun setupView()

    fun navigate(flow: NavigationFlow) {
        flowNavigator.navigateToFlow(flow)
    }

    fun <T> LiveData<T>.observedNonNull(owner: LifecycleOwner, f: (T) -> Unit) {
        this.observe(owner, Observer<T> { t -> t?.let(f) })
    }
}