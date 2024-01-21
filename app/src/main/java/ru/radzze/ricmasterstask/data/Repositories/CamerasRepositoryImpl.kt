package ru.radzze.ricmasterstask.data.Repositories

import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.delay
import kotlinx.serialization.decodeFromString
import ru.radzze.ricmasterstask.model.Camera
import ru.radzze.ricmasterstask.model.CamerasResponse
import ru.radzze.ricmasterstask.model.DoorsResponse
import javax.inject.Inject

class CamerasRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
):CamerasRepository {
    override suspend fun getCameras(): MutableMap<String,MutableList<Camera>> {
        var camerasList = mutableMapOf<String,MutableList<Camera>>()
        try {
            val resposne = httpClient.get("http://cars.cprogroup.ru/api/rubetek/cameras"){
                contentType(ContentType.Application.Json)
            }.body<CamerasResponse>()
            resposne.data.room.forEach {
                camerasList.put(it, mutableListOf<Camera>())
                resposne.data.cameras.forEach { camera ->
                    if(camera.room == it){
                        camerasList[it]?.add(camera)
                    }
                }
            }
        } catch (e: NoTransformationFoundException) {
            delay(2000)
            getCameras()
        }
        return camerasList
    }

    override fun saveCamera(camera:Camera) {
        TODO("Not yet implemented")
    }

    override fun updateCamera() {
        TODO("Not yet implemented")
    }
}