package furkan.satellite_list.presentation.satellite.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import furkan.satellite_list.data.satellite.repository.FakeSatelliteRepository
import furkan.satellite_list.domain.satellite.mapper.SatelliteEntityMapper
import furkan.satellite_list.domain.satellite.usecase.FakeGetFilteredSatelliteUseCase
import furkan.satellite_list.domain.satellite.usecase.FakeGetSatelliteUseCase
import furkan.satellite_list.getSatelliteFakeData
import junit.framework.TestCase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SatelliteViewModelTest {

    private lateinit var viewModel: SatelliteViewModel
    private lateinit var useCase: FakeGetSatelliteUseCase
    private lateinit var useCaseFiltered: FakeGetFilteredSatelliteUseCase
    private lateinit var repository: FakeSatelliteRepository
    private val satelliteUiMapper = SatelliteUiMapper()
    private val satelliteEntityMapper = SatelliteEntityMapper()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = FakeSatelliteRepository(getSatelliteFakeData(true))
        useCase = FakeGetSatelliteUseCase(repository, satelliteEntityMapper)
        useCaseFiltered = FakeGetFilteredSatelliteUseCase(repository, satelliteEntityMapper)
        viewModel = SatelliteViewModel(useCase, useCaseFiltered, satelliteUiMapper)
    }

    @Test
    fun `get satellite list returns true`() = runTest() {
        var data: List<SatelliteUiData>? = null
        val expectedResult =
            satelliteUiMapper.map(satelliteEntityMapper.map(getSatelliteFakeData(true)))
        val job = launch {
            viewModel.getSatellite().take(2).collect {
                data = it.data
            }
            assertTrue(data == expectedResult)
        }
        job.cancel()
    }

    @Test
    fun `get satellite list returns false`() = runTest {
        var data: List<SatelliteUiData>? = null
        val expectedResult =
            satelliteUiMapper.map(satelliteEntityMapper.map(getSatelliteFakeData(false)))
        val job = launch() {
            viewModel.getSatellite().take(2).collect {
                data = it.data
            }
            assertFalse(data == expectedResult)
        }
        job.cancel()
    }

    @Test
    fun `get satellite list filter returns true`() = runTest {
        val searchValue = "Dra"
        var data: List<SatelliteUiData>? = null
        val expectedResult =
            satelliteUiMapper.map(satelliteEntityMapper.map(getSatelliteFakeData(true)))
                .filter { it.name.contains(searchValue, true) }
        val job = launch() {
            viewModel.getSearchedSatellite(searchValue).take(2).collect {
                data = it.data
            }
            assertTrue(data == expectedResult)
        }
        job.cancel()

    }

    @Test
    fun `get satellite list filter returns false`() = runTest {
        val searchValue = "Dra"
        var data: List<SatelliteUiData>? = null
        val expectedResult =
            satelliteUiMapper.map(satelliteEntityMapper.map(getSatelliteFakeData(false)))
                .filter { it.name.contains(searchValue, true) }
        val job = launch() {
            viewModel.getSearchedSatellite(searchValue).take(2).collect {
                data = it.data
            }
            assertFalse(data == expectedResult)
        }
        job.cancel()
    }
}

