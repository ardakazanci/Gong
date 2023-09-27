package com.ardakazanci.gong.presentation

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ardakazanci.gong.R
import com.ardakazanci.gong.core.SearchHelper
import com.ardakazanci.gong.core.navigation.NavigationFlow
import com.ardakazanci.gong.core.presentation.MotherFragment
import com.ardakazanci.gong.core.presentation.MotherViewModel
import com.ardakazanci.gong.databinding.ListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class ListFragment : MotherFragment<ListFragmentBinding>(ListFragmentBinding::inflate) {

    private val listAdapter: ListAdapter by lazy {
        ListAdapter { id,name ->
            navigate(NavigationFlow.DetailFlow(id,name))
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

        vm.filteredItems.observedNonNull(viewLifecycleOwner){
            listAdapter.submitList(it)
        }

        binding.searchEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                vm.loading(true)
                val searchTerm = s.toString().lowercase(Locale.ROOT)
                takeIf { searchTerm.isNotEmpty() }?.run {
                    SearchHelper.debounce<Unit>(1500,viewLifecycleOwner.lifecycleScope){
                        val filteredList =
                            vm.filteredItems.value?.filter { item ->
                                item.name.lowercase(Locale.ROOT).contains(
                                    s.toString().lowercase(
                                        Locale.ROOT
                                    )
                                )
                            }
                        vm.filteredItems.value = filteredList
                        vm.loading(false)
                    }.invoke(Unit)
                }

                takeIf { searchTerm.isEmpty() }?.run {
                    vm.filteredItems.value = vm.originalItems.value
                    listAdapter.submitList(vm.originalItems.value)
                    vm.loading(false)
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // not used
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // not used
            }
        })
    }
}