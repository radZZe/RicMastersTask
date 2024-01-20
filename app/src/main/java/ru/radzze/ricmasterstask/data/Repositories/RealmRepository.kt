package ru.radzze.ricmasterstask.data.Repositories

import kotlinx.coroutines.flow.Flow
import ru.radzze.ricmasterstask.model.Camera
import ru.radzze.ricmasterstask.model.Door


interface RealmRepository {
    fun getCameras(): Flow<List<Camera>>
    fun getDoors(): Flow<List<Door>>
    suspend fun updateCamera(camera: Camera)
    suspend fun insertCamera(camera: Camera)
    suspend fun insertDoor(door: Door)
    suspend fun updateDoor(door: Door)
}