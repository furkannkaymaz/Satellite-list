package furkan.satellite_list.presentation.detail.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
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
import furkan.satellite_list.utils.helper.hide
import furkan.satellite_list.utils.helper.show
import furkan.satellite_list.utils.response.UIStatus
import kotlinx.android.synthetic.main.fragment_satellite.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteDetailFragment :
    BaseFragment<FragmentSatelliteDetailBinding, SatelliteDetailViewModel>() {

    override val viewModel: SatelliteDetailViewModel by viewModels()
    private val args: SatelliteDetailFragmentArgs by navArgs()
    var satelliteId: Int = 0

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
                        configureDetail(it.data)
                        flProgress.hide()
                        viewModel.addSatelliteDetail(it.data!!)
                    }
                    UIStatus.ERROR -> {
                        requireContext() toast getString(R.string.errorMessage)
                    }
                    UIStatus.LOADING -> {
                        flProgress.show()
                    }
                }
            }

        }

        lifecycleScope.launch {
            viewModel.getSatellitePosition(satelliteId).collectLatest {
                when (it.state) {
                    UIStatus.SUCCESS -> {
                        configurePosition(it.data)
                        flProgress.hide()
                    }
                    UIStatus.ERROR -> {
                        requireContext() toast getString(R.string.errorMessage)
                    }
                    UIStatus.LOADING -> {
                        flProgress.show()
                    }
                }
            }
        }
    }


    private fun configureDetail(data: SatelliteDetailUiData?) {
        binding?.apply {
            tvName.text = args.name
            tvDate.text = data?.first_flight
            tvHeightMass.text = Html.fromHtml(
                "<b>${getString(R.string.heightMass)}</b> :${data?.height} / ${data?.mass}",
                0
            )
            tvCost.text = Html.fromHtml(
                "<b>${getString(R.string.cost)}</b> : ${data?.cost_per_launch}",
                0
            )
        }
    }


    private fun configurePosition(data: SatellitePositionUiData?) {
        binding?.apply {
            tvLastPosition.text = Html.fromHtml(
                "<b>${getString(R.string.lastPosition)}</b> : (${data?.positions?.get(0)?.posX},${
                    data?.positions?.get(0)?.posY
                })"
            )

        }
    }

    override fun layoutResource(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSatelliteDetailBinding {
        return FragmentSatelliteDetailBinding.inflate(inflater, container, false)
    }
}