package furkan.satellite_list

import furkan.satellite_list.data.detail.dto.PositionModel
import furkan.satellite_list.data.detail.dto.SatelliteDetailModel
import furkan.satellite_list.data.detail.dto.SatellitePositionModel
import furkan.satellite_list.data.satellite.dto.SatelliteModel

fun getSatelliteFakeData(success: Boolean): List<SatelliteModel> {
    return if (success) {
        val fakeData = SatelliteModel(1, true, "Starship")
        val fakeData1 = SatelliteModel(2, true, "Stars")
        val fakeData2 = SatelliteModel(3, true, "Dragon")
        listOf(fakeData, fakeData1, fakeData2)
    } else {
        val fakeData = SatelliteModel(0, true, "Starship")
        val fakeData1 = SatelliteModel(0, true, "Starship2")
        val fakeData2 = SatelliteModel(0, true, "Starship3")
        listOf(fakeData, fakeData1, fakeData2)
    }
}

fun getSatelliteDetailFakeData(id: Int): SatelliteDetailModel {
    val fakeData = SatelliteDetailModel(1, 0, "02.02.2022", 100, 200)
    val fakeData1 = SatelliteDetailModel(2, 500, "02.03.2022", 200, 400)
    val fakeData2 = SatelliteDetailModel(3, 100, "05.02.2022", 300, 280)
    val fakeDataList = listOf(fakeData, fakeData1, fakeData2)

    return if (id < 0){
        SatelliteDetailModel(null,null,null,null,null)
    }else{
        fakeDataList.find { it.id == id }!!
    }
}

fun getSatellitePositionFakeData(id: Int): SatellitePositionModel {
    val fakeData = SatellitePositionModel("1", listOf(PositionModel(100.0, 200.0)))
    val fakeData1 = SatellitePositionModel("2", listOf(PositionModel(100.0, 200.0)))
    val fakeData2 = SatellitePositionModel("3", listOf(PositionModel(100.0, 200.0)))
    val fakeDataList = listOf(fakeData, fakeData1, fakeData2)

    return if (id < 0){
        SatellitePositionModel(null, listOf(PositionModel(null,null)))
    }else{
        fakeDataList.find { it.id == id.toString() }!!
    }
}





