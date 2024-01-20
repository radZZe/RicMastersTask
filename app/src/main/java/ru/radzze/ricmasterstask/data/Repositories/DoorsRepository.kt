package ru.radzze.ricmasterstask.data.Repositories

import ru.radzze.ricmasterstask.model.Door

interface DoorsRepository {
    suspend fun getDoors():List<Door>
    fun saveDoor()
    fun updateDoor()
}