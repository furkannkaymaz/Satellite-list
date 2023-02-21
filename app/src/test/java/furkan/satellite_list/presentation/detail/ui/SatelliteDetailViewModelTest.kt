package furkan.satellite_list.presentation.detail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import furkan.satellite_list.data.detail.repository.FakeSatelliteDetailRepository
import furkan.satellite_list.data.detail.repository.FakeSatellitePositionRepository
import furkan.satellite_list.domain.detail.mapper.SatelliteDetailEntityMapper
import furkan.satellite_list.domain.detail.mapper.SatellitePositionEntityMapper
import furkan.satellite_list.domain.detail.usecase.FakeGetSatelliteDetailUseCase
import furkan.satellite_list.domain.detail.usecase.FakeGetSatellitePositionUseCase
import furkan.satellite_list.getSatelliteDetailFakeData
import furkan.satellite_list.getSatellitePositionFakeData
import furkan.satellite_list.utils.response.Resource
import furkan.satellite_list.utils.response.UIStatus
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SatelliteDetailViewModelTest {

    private lateinit var viewModel: SatelliteDetailViewModel
    private lateinit var fakeGetSatellitePositionUseCase: FakeGetSatellitePositionUseCase
    private lateinit var fakeGetSatelliteDetailUseCase: FakeGetSatelliteDetailUseCase
    private lateinit var fakeSatelliteDetailRepository: FakeSatelliteDetailRepository
    private lateinit var fakeSatellitePositionRepository: FakeSatellitePositionRepository
    private val satelliteDetailUiMapper = SatelliteDetailUiMapper()
    private val satelliteDetailEntityMapper = SatelliteDetailEntityMapper()
    private val satellitePositionEntityMapper = SatellitePositionEntityMapper()
    private val satellitePositionUiMapper = SatellitePositionUiMapper()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        fakeSatelliteDetailRepository =
            FakeSatelliteDetailRepository(getSatelliteDetailFakeData(1))
        fakeSatellitePositionRepository =
            FakeSatellitePositionRepository(getSatellitePositionFakeData(1))
        fakeGetSatellitePositionUseCase = FakeGetSatellitePositionUseCase(
            fakeSatellitePositionRepository,
            satellitePositionEntityMapper
        )
        fakeGetSatelliteDetailUseCase = FakeGetSatelliteDetailUseCase(
            fakeSatelliteDetailRepository,
            satelliteDetailEntityMapper
        )
        viewModel = SatelliteDetailViewModel(
            fakeGetSatelliteDetailUseCase,
            fakeGetSatellitePositionUseCase,
            satelliteDetailUiMapper,
            satellitePositionUiMapper,
            fakeSatelliteDetailRepository
        )
    }

    @Test
    fun `when getSatelliteDetailAndPosition is called with valid id, then it should return satellite detail and position`() =
        runTest {
            // Given
            val id = 1
            val expectedDetail = satelliteDetailUiMapper.map(
                satelliteDetailEntityMapper.map(getSatelliteDetailFakeData(id))
            )
            val expectedPosition = satellitePositionUiMapper.map(
                satellitePositionEntityMapper.map(getSatellitePositionFakeData(id))
            )
            val expectedPair = Pair(
                Resource.Success(expectedDetail, UIStatus.SUCCESS),
                Resource.Success(expectedPosition, UIStatus.SUCCESS)
            )

            var detailResult: SatelliteDetailUiData? = null
            var positionResult: SatellitePositionUiData? = null

            // When
            val result = viewModel.getSatelliteDetailAndPosition(id)

            launch {
                result.first.take(2).collectLatest {
                    detailResult = it.data

                }
            }
            launch {
                result.second.take(2).collectLatest {
                    positionResult = it.data
                }
                assertTrue(detailResult == expectedPair.first.data)
                assertTrue(positionResult == expectedPair.second.data)
            }
        }

}