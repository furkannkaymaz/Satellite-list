package furkan.satellite_list.presentation.satellite.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import furkan.satellite_list.utils.extensions.toast
import furkan.satellite_list.utils.response.UIStatus
import dagger.hilt.android.AndroidEntryPoint
import furkan.satellite_list.databinding.FragmentSatelliteBinding
import furkan.satellite_list.presentation.base.BaseFragment
import furkan.satellite_list.utils.extensions.listen
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteFragment : BaseFragment<FragmentSatelliteBinding,SatelliteViewModel>() {

    override val viewModel: SatelliteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.getSatellite().listen {
                when (it.state) {
                    UIStatus.SUCCESS -> {
                        requireContext() toast "SUCCESS"
                    }
                    UIStatus.ERROR -> {
                        requireContext() toast "ERROR"
                    }
                    UIStatus.LOADING -> {
                        requireContext() toast "LOADING"
                    }
                    else -> {
                    }
                }
            }
        }
    }

    override fun layoutResource(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSatelliteBinding {
        return FragmentSatelliteBinding.inflate(inflater,container,false)
    }

}