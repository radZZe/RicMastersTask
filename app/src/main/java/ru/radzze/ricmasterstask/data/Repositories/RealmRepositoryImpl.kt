package ru.radzze.ricmasterstask.data.Repositories

import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
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

    override suspend fun updateCamera(camera: Camera):Boolean {
        var flag = false
        realm.write {
            val queriedCamera = query<Camera>(query = "id == $0", camera.id).first().find()
            if(queriedCamera == null){
                flag = false
            }else{
                queriedCamera.name = camera.name
                queriedCamera.room = camera.room
                queriedCamera.snapshot = camera.snapshot
                queriedCamera.rec = camera.rec
                queriedCamera.favorites = camera.favorites
                flag = true
            }

        }
        return flag
    }

    override suspend fun insertCamera(camera: Camera) {
        realm.write { copyToRealm(camera, updatePolicy = UpdatePolicy.ALL) }
    }

    override suspend fun insertDoor(door: Door) {
        realm.write { copyToRealm(door,updatePolicy = UpdatePolicy.ALL) }
    }

    override suspend fun updateDoor(door: Door):Boolean {
        var flag = false
        realm.write {
            val queriedDoor = query<Door>(query = "id == $0", door.id).first().find()
            if(queriedDoor == null){
                flag =  false
            }else{
                queriedDoor.room = door.room
                queriedDoor.snapshot = door.snapshot
                queriedDoor.name = door.name
                queriedDoor.favorites = door.favorites
                flag =  true
            }

        }
        return flag
    }
}