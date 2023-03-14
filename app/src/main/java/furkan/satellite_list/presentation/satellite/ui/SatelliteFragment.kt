package furkan.satellite_list.presentation.satellite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import furkan.satellite_list.utils.extensions.toast
import furkan.satellite_list.utils.response.UIStatus
import dagger.hilt.android.AndroidEntryPoint
import furkan.satellite_list.R
import furkan.satellite_list.databinding.FragmentSatelliteBinding
import furkan.satellite_list.presentation.base.BaseFragment
import furkan.satellite_list.presentation.detail.ui.SatelliteDetailFragmentArgs
import furkan.satellite_list.presentation.satellite.adapter.SatelliteAdapter
import furkan.satellite_list.utils.extensions.listen
import furkan.satellite_list.utils.helper.hide
import furkan.satellite_list.utils.helper.isSearchable
import furkan.satellite_list.utils.helper.show
import kotlinx.android.synthetic.main.fragment_satellite.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SatelliteFragment : BaseFragment<FragmentSatelliteBinding, SatelliteViewModel>() {

    override val viewModel: SatelliteViewModel by viewModels()

    @Inject lateinit var satelliteAdapter: SatelliteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.getSatellite().listen {
                when (it.state) {
                    UIStatus.SUCCESS -> {
                        searchText()
                        setAdapter()
                        satelliteAdapter.submitList(it.data)
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

    private fun setAdapter() {

        satelliteAdapter.setOnClick {
            goDetailPage(it.id, it.name)
        }

        binding?.rvSatellite?.adapter = satelliteAdapter
        binding?.rvSatellite?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    private fun searchText() {

        binding?.searchView?.handleSearchText {
            if (!isSearchable(it)) {
                return@handleSearchText
            }
            viewModel.getSearchedSatellite(it)
        }
    }

    private fun goDetailPage(id: Int, name: String) {
        findNavController().navigate(
            R.id.action_satelliteFragment_to_satelliteDetailFragment,
            SatelliteDetailFragmentArgs(id, name).toBundle()
        )
    }

    override fun layoutResource(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSatelliteBinding {
        return FragmentSatelliteBinding.inflate(inflater, container, false)
    }

}