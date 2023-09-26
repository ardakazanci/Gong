package com.ardakazanci.gong.presentation

import androidx.fragment.app.viewModels
import com.ardakazanci.gong.core.presentation.MotherFragment
import com.ardakazanci.gong.core.presentation.MotherViewModel
import com.ardakazanci.gong.databinding.ListFragmentBinding

class ListFragment : MotherFragment<ListFragmentBinding>(ListFragmentBinding::inflate) {

    private val listAdapter: ListAdapter by lazy {
        ListAdapter { id ->

        }
    }

    private val vm: ListViewModel by viewModels()

    override fun mViewModel(): MotherViewModel = vm

    override fun setupView() {
        binding.rcList.apply{
            adapter = listAdapter
        }
    }

}