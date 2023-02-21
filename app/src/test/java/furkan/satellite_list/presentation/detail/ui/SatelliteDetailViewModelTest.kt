package furkan.satellite_list.presentation.detail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import furkan.satellite_list.data.detail.db.SatelliteDetailDao
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.eq
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


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
    private lateinit var mockDao: SatelliteDetailDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        mockDao = Mockito.mock(SatelliteDetailDao::class.java)
        fakeSatelliteDetailRepository =
            FakeSatelliteDetailRepository(getSatelliteDetailFakeData(1),mockDao)
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

            val job = launch {
                result.first.take(2).collectLatest {
                    detailResult = it.data

                }
            }
            val job2 =launch {
                result.second.take(2).collectLatest {
                    positionResult = it.data
                }
                assertTrue(detailResult == expectedPair.first.data)
                assertTrue(positionResult == expectedPair.second.data)
            }
            job.cancel()
            job2.cancel()
        }

    @Test
    fun `when getSatelliteDetailAndPosition is called with invalid id, then it should return error result`() = runTest(
        StandardTestDispatcher()
    ) {
        val id = -1
        val expectedDetail = null
        val expectedPosition = null
        val expectedPair = Pair(
            Resource.Error(expectedDetail, UIStatus.ERROR),
            Resource.Error(expectedPosition, UIStatus.ERROR)
        )

        var detailResult: Int? = null
        var positionResult: Int? = null

        val result = viewModel.getSatelliteDetailAndPosition(id)

        launch(Dispatchers.Unconfined) {
            result.first.take(2).collectLatest {
                detailResult = it.data?.id
            }
        }
        launch(Dispatchers.Unconfined) {
            result.second.take(2).collectLatest {
                positionResult = it.data?.id?.toInt()

                assertTrue(detailResult == expectedPair.first.data)
                assertTrue(positionResult == expectedPair.second.data)
            }
        }
    }

    @Test
    fun `when result`() = runTest{
        val data = getSatelliteDetailFakeData(1)

        whenever(mockDao.insertSatelliteDetail(data)).thenReturn(Unit)

        viewModel.addSatelliteDetail(satelliteDetailUiMapper.map(satelliteDetailEntityMapper.map(data)))

        verify(mockDao, times(1)).insertSatelliteDetail(eq(data))
    }

}