package ru.radzze.ricmasterstask.data
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RubetekHttpClient @Inject constructor() {

    @OptIn(ExperimentalSerializationApi::class)
    fun getHttpClient() = HttpClient(CIO) {
        install(Logging)

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                explicitNulls = false
            })
        }
        install(WebSockets)

    }



}

