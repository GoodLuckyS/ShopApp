package com.example.shopapp.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.shopapp.MainApplication
import com.example.shopapp.domain.AppError
import com.example.shopapp.presentation.entity.UIState
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB,
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB get() = _binding as VB

    val appComponent by lazy {
        (requireActivity().application as MainApplication).appComponent
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = bindingInflater(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        setupSubscribes()

    }


    protected open fun initialize() {}
    protected open fun setupSubscribes() {}
    protected open fun setupListeners() = with(binding) {}

    protected fun <T> SharedFlow<T>.observe(action: suspend (T) -> Unit) {
        collectFlowSafely(Lifecycle.State.STARTED) {
            this.collect {
                action.invoke(it)
            }
        }
    }

    protected fun <T> StateFlow<T>.observe(action: suspend (T) -> Unit) {
        collectFlowSafely(Lifecycle.State.STARTED) {
            this.collect {
                action.invoke(it)
            }
        }
    }

    protected fun <T> UIState<T>.setupViewVisibility(
        group: Group, loader: ProgressBar, isNavigateWhenSuccess: Boolean = false,
    ) {
        fun showLoader(isVisible: Boolean) {
            group.visibility = if (isVisible) View.INVISIBLE else View.VISIBLE
            loader.visibility = if (!isVisible) View.INVISIBLE else View.VISIBLE
        }

        when (this) {
            is UIState.Idle -> {}
            is UIState.Loading -> showLoader(true)
            is UIState.Error -> showLoader(false)
            is UIState.Success -> if (!isNavigateWhenSuccess) showLoader(false)
        }
    }


    fun <T> UIState<T>.onSuccess(
        block: (data: T) -> Unit,
    ): UIState<T> {
        if (this is UIState.Success) {
            block(this.data)
        }
        return this
    }

    fun <T> UIState<T>.onError(
        block: (data: AppError) -> Unit,
    ): UIState<T> {
        if (this is UIState.Error) {
            block(this.error)
        }
        return this
    }

    private fun collectFlowSafely(
        lifecycleState: Lifecycle.State,
        collect: suspend () -> Unit,
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(lifecycleState) {
                collect()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun AppError.setupApiErrors(vararg inputs: TextInputLayout) {
        if (this is AppError.Api) {
            for (input in inputs) {
                input.error = message
            }
        }
    }

    fun AppError.setupUnexpectedErrors(context: Context) {
        if (this is AppError.Unexpected) {
            Toast.makeText(context, this.message, Toast.LENGTH_LONG).show()
        }
    }

}