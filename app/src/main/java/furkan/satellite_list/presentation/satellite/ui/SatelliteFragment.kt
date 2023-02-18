package furkan.satellite_list.presentation.satellite.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import furkan.satellite_list.utils.extensions.toast
import furkan.satellite_list.utils.response.UIStatus
import dagger.hilt.android.AndroidEntryPoint
import furkan.satellite_list.R
import furkan.satellite_list.databinding.FragmentSatelliteBinding
import furkan.satellite_list.presentation.base.BaseFragment
import furkan.satellite_list.presentation.detail.ui.SatelliteDetailFragment
import furkan.satellite_list.presentation.detail.ui.SatelliteDetailFragmentArgs
import furkan.satellite_list.presentation.satellite.adapter.SatelliteAdapter
import furkan.satellite_list.utils.extensions.listen
import furkan.satellite_list.utils.helper.isSearchable
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatelliteFragment : BaseFragment<FragmentSatelliteBinding, SatelliteViewModel>() {

    override val viewModel: SatelliteViewModel by viewModels()

    private lateinit var satelliteAdapter: SatelliteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        searchText()
        lifecycleScope.launch {
            viewModel.getSatellite().listen {
                when (it.state) {
                    UIStatus.SUCCESS -> {
                        satelliteAdapter.submitList(it.data)
                        requireContext() toast "SUCCESS"
                    }
                    UIStatus.ERROR -> {

                    }
                    UIStatus.LOADING -> {
                        requireContext() toast "LOADING"
                    }
                }
            }
        }
    }

    private fun setAdapter() {

        satelliteAdapter = SatelliteAdapter()
        satelliteAdapter.setOnClick {
            goDetailPage(it)
        }

        binding?.rvSatellite?.adapter = satelliteAdapter
        binding?.rvSatellite?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    private fun searchText() {

        binding?.searchView?.handleSearchText {
            if (!isSearchable(it)){
                return@handleSearchText
            }
            viewModel.getSearchedSatellite(it)
        }
    }

    private fun goDetailPage(id: Int) {
        findNavController().navigate(
            R.id.action_satelliteFragment_to_satelliteDetailFragment,
            SatelliteDetailFragmentArgs(id).toBundle()
        )
    }

    override fun layoutResource(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSatelliteBinding {
        return FragmentSatelliteBinding.inflate(inflater, container, false)
    }

}