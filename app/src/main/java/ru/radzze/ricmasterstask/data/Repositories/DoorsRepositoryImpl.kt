package ru.radzze.ricmasterstask.data.Repositories

import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.delay
import ru.radzze.ricmasterstask.model.Camera
import ru.radzze.ricmasterstask.model.CamerasResponse
import ru.radzze.ricmasterstask.model.Door
import ru.radzze.ricmasterstask.model.DoorsResponse
import javax.inject.Inject

class DoorsRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
):DoorsRepository {
    override suspend fun getDoors():List<Door> {
        var doorsList = listOf<Door>()
        try {
            doorsList = httpClient.get("http://cars.cprogroup.ru/api/rubetek/doors"){
                contentType(ContentType.Application.Json)
            }.body<DoorsResponse>().data
        } catch (e: NoTransformationFoundException) {
            delay(2000)
            getDoors()
        }
        return doorsList

    }

    override fun saveDoor() {
        TODO("Not yet implemented")
    }

    override fun updateDoor() {
        TODO("Not yet implemented")
    }
}