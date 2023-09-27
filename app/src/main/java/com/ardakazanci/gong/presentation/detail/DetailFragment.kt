package com.ardakazanci.gong.presentation.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ardakazanci.gong.core.presentation.MotherFragment
import com.ardakazanci.gong.core.presentation.MotherViewModel
import com.ardakazanci.gong.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : MotherFragment<DetailFragmentBinding>(DetailFragmentBinding::inflate) {

    private val vm: DetailViewModel by viewModels()

    override fun mViewModel(): MotherViewModel = vm

    val args by navArgs<DetailFragmentArgs>()

    override fun setupView() {
        val idValue = args.idValue
        val nameValue = args.name
        vm.invokeAction(DetailUiState.Action.GetDetail(idValue))

        vm.uiStateData.observedNonNull(viewLifecycleOwner) { state ->
            when (state) {
                is DetailUiState.State.GetDetail -> {
                    with(binding){
                        title.text = nameValue
                        date.text = state.detail.firstFlight
                        heightMassValue.text = state.detail.height.toString().plus("/${state.detail.mass}")
                        costValue.text = state.detail.costPerLaunch.toString()
                    }
                }
                is DetailUiState.State.GetPositions -> {
                    with(binding){
                        lastPositionValue.text = state.positions.positions.first().posX.toString().plus("${state.positions.positions.first().posY}")
                    }
                }
            }
        }

    }

}