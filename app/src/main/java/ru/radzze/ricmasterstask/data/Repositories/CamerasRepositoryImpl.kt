package ru.radzze.ricmasterstask.data.Repositories

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.radzze.ricmasterstask.model.Camera
import ru.radzze.ricmasterstask.model.CamerasResponse
import ru.radzze.ricmasterstask.model.DoorsResponse
import javax.inject.Inject

class CamerasRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
):CamerasRepository {
    override suspend fun getCameras(): List<Camera> {
        return httpClient.get("http://cars.cprogroup.ru/api/rubetek/cameras").body<CamerasResponse>().data.cameras
    }

    override fun saveCamera() {
        TODO("Not yet implemented")
    }

    override fun updateCamera() {
        TODO("Not yet implemented")
    }
}