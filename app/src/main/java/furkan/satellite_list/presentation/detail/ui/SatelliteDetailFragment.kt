package furkan.satellite_list.presentation.detail.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import furkan.satellite_list.R
import furkan.satellite_list.databinding.FragmentSatelliteDetailBinding
import furkan.satellite_list.presentation.base.BaseFragment
import furkan.satellite_list.utils.extensions.listen
import furkan.satellite_list.utils.extensions.toast
import furkan.satellite_list.utils.response.UIStatus
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailFragment : BaseFragment<FragmentSatelliteDetailBinding,SatelliteDetailViewModel>() {

    override val viewModel: SatelliteDetailViewModel by viewModels()
    private val args: SatelliteDetailFragmentArgs by navArgs()
    var satelliteId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        satelliteId = args.id
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            viewModel.getSatelliteDetail(satelliteId).collectLatest {
                when (it.state) {
                    UIStatus.SUCCESS -> {
                        Log.d("data1",it.data?.first_flight.toString())
                    }
                    UIStatus.ERROR -> {

                    }
                    UIStatus.LOADING -> {

                    }
                }
            }

        }

        lifecycleScope.launch {
            viewModel.getSatellitePosition(satelliteId).collectLatest {
                when (it.state) {
                    UIStatus.SUCCESS -> {
                        Log.d("data1",it.data?.positions?.get(0)?.posY.toString())
                    }
                    UIStatus.ERROR -> {

                    }
                    UIStatus.LOADING -> {
                    }
                }
            }
        }
    }

    override fun layoutResource(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSatelliteDetailBinding {
        return FragmentSatelliteDetailBinding.inflate(inflater,container,false)
    }
}