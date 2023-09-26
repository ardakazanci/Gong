package com.ardakazanci.gong.presentation

import android.util.Log
import androidx.fragment.app.viewModels
import com.ardakazanci.gong.R
import com.ardakazanci.gong.core.presentation.MotherFragment
import com.ardakazanci.gong.core.presentation.MotherViewModel
import com.ardakazanci.gong.databinding.ListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : MotherFragment<ListFragmentBinding>(ListFragmentBinding::inflate) {

    private val listAdapter: ListAdapter by lazy {
        ListAdapter { id ->

        }
    }

    private val vm: ListViewModel by viewModels()

    override fun mViewModel(): MotherViewModel = vm

    override fun setupView() {
        binding.rcList.apply {
            adapter = listAdapter
            addItemDecoration(CustomDivider(requireContext(), R.drawable.custom_divider))
        }

        vm.uiStateData.observedNonNull(viewLifecycleOwner) { state ->
            when (state) {
                is ListUiState.State.GetList -> {
                    listAdapter.submitList(state.list)
                }
            }
        }
    }
}