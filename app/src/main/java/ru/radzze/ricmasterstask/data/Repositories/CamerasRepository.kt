package ru.radzze.ricmasterstask.data.Repositories

import ru.radzze.ricmasterstask.model.Camera
import ru.radzze.ricmasterstask.model.Door

interface CamerasRepository {
    suspend fun getCameras():List<Camera>
    fun saveCamera()
    fun updateCamera()
}