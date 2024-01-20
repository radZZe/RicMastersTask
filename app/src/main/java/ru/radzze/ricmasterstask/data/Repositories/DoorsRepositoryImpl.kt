package ru.radzze.ricmasterstask.data.Repositories

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import ru.radzze.ricmasterstask.model.Door
import ru.radzze.ricmasterstask.model.DoorsResponse
import javax.inject.Inject

class DoorsRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
):DoorsRepository {
    override suspend fun getDoors():List<Door> {
        return httpClient.get("http://cars.cprogroup.ru/api/rubetek/doors").body<DoorsResponse>().data
    }

    override fun saveDoor() {
        TODO("Not yet implemented")
    }

    override fun updateDoor() {
        TODO("Not yet implemented")
    }
}