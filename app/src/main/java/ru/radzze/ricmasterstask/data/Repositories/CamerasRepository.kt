package ru.radzze.ricmasterstask.data.Repositories

import ru.radzze.ricmasterstask.model.Camera
import ru.radzze.ricmasterstask.model.Door

interface CamerasRepository {
    suspend fun getCameras():MutableMap<String,MutableList<Camera>>
    fun saveCamera(camera:Camera)
    fun updateCamera()
}