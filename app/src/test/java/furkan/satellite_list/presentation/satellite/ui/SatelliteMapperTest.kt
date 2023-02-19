package furkan.satellite_list.presentation.satellite.ui

import furkan.satellite_list.data.satellite.dto.SatelliteModel
import furkan.satellite_list.domain.satellite.entity.SatelliteEntity
import furkan.satellite_list.domain.satellite.mapper.SatelliteEntityMapper
import furkan.satellite_list.getSatelliteFakeData
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class SatelliteMapperTest{

    private  var satelliteUiMapper = SatelliteUiMapper()
    private  var satelliteEntityMapper = SatelliteEntityMapper()
    private lateinit var satelliteEntity : List<SatelliteEntity>
    private lateinit var satelliteUiData : List<SatelliteUiData>
    private lateinit var satelliteModel : List<SatelliteModel>

    @Before
    fun setup(){
        satelliteModel = getSatelliteFakeData(true)
        satelliteEntity = satelliteEntityMapper.map(satelliteModel)
        satelliteUiData = satelliteUiMapper.map(satelliteEntity)
    }

    @Test
    fun `name mapping success when SatelliteUiMapper is same`() {
        assertEquals(satelliteEntity.first().name,satelliteUiData.first().name)
    }

    @Test
    fun `name mapping fail when SatelliteUiMapper is not same`() {
        assertNotEquals(satelliteEntity.first().id,satelliteUiData.first().active)
    }

    @Test
    fun `active mapping success when SatelliteEntityMapper is same`() {
        assertEquals(satelliteModel.first().active,satelliteModel.first().active)
    }

    @Test
    fun `active mapping fail when SatelliteEntityMapper is not same`() {
        assertNotEquals(satelliteModel.first().active,satelliteModel.first().id)
    }

}