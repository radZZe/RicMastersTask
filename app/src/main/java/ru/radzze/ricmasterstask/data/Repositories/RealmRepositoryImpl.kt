package ru.radzze.ricmasterstask.data.Repositories

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.radzze.ricmasterstask.model.Camera
import ru.radzze.ricmasterstask.model.Door

class RealmRepositoryImpl(val realm: Realm):RealmRepository {
    override fun getCameras(): Flow<List<Camera>> {
        return realm.query<Camera>().asFlow().map { it.list }
    }

    override fun getDoors(): Flow<List<Door>> {
        return realm.query<Door>().asFlow().map { it.list }
    }

    override suspend fun updateCamera(camera: Camera) {
        realm.write {
            val queriedCamera = query<Camera>(query = "_id == $0", camera.id).first().find()
            queriedCamera?.favorites = camera.favorites
        }
    }

    override suspend fun insertCamera(camera: Camera) {
        realm.write { copyToRealm(camera) }
    }

    override suspend fun insertDoor(door: Door) {
        realm.write { copyToRealm(door) }
    }

    override suspend fun updateDoor(door: Door) {
        realm.write {
            val queriedDoor = query<Camera>(query = "_id == $0", door.id).first().find()
            queriedDoor?.name = door.name
            queriedDoor?.favorites = door.favorites
        }
    }
}