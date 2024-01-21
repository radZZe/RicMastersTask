package ru.radzze.ricmasterstask.ui

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.radzze.ricmasterstask.R
import ru.radzze.ricmasterstask.data.Repositories.CamerasRepository
import ru.radzze.ricmasterstask.data.Repositories.DoorsRepository
import ru.radzze.ricmasterstask.data.Repositories.RealmRepository
import ru.radzze.ricmasterstask.model.Camera
import ru.radzze.ricmasterstask.model.Door
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    @ApplicationContext application: Context,
    private val doorRep: DoorsRepository,
    private val cameraRep: CamerasRepository,
    private val dbRep: RealmRepository
) : ViewModel() {
    val tabList: List<String> =
        listOf(application.getString(R.string.cameras), application.getString(R.string.doors))
    var selectedTabIndex by mutableStateOf(0)
    var camerasList: MutableLiveData<MutableMap<String, MutableList<Camera>>> =
        MutableLiveData<MutableMap<String, MutableList<Camera>>>(mutableMapOf<String, MutableList<Camera>>())
    var doorsList: MutableLiveData<List<Door>> = MutableLiveData<List<Door>>(listOf<Door>())

     fun getDoors() {
         viewModelScope.launch(Dispatchers.IO) {
             var list = listOf<Door>()
             dbRep.getDoors().collect {
                 list = it
                 if (!list.isEmpty()) {
                     withContext(Dispatchers.Main){
                         doorsList.value = it
                     }

                     updateData()
                 }
             }
             if (list.isEmpty()) {
                 withContext(Dispatchers.Main){
                     doorsList.value = doorRep.getDoors()
                 }

                 doorsList.value!!.forEach {
                     dbRep.insertDoor(it)
                 }
             }
         }


    }

     fun getCameras() {
         viewModelScope.launch(Dispatchers.IO) {
             var list = listOf<Camera>()
             dbRep.getCameras().collect {
                 list = it
                 if (!list.isEmpty()) {
                     withContext(Dispatchers.Main){
                         convertListToMap(it)
                     }
                     updateData()
                 }
             }
             if (list.isEmpty()) {
                 withContext(Dispatchers.Main){
                     camerasList.value = cameraRep.getCameras()
                 }
                 camerasList.value!!.forEach { it ->
                     it.value.forEach { item ->
                         dbRep.insertCamera(item)
                     }
                 }
             }
         }

    }


    fun updateData() {
        viewModelScope.launch(Dispatchers.IO) {
            cameraRep.getCameras().forEach {
                it.value.forEach { item ->
                    val result = dbRep.updateCamera(item)
                    if (!result) dbRep.insertCamera(item)
                }

            }
            doorRep.getDoors().forEach {
                val result = dbRep.updateDoor(it)
                if (!result) dbRep.insertDoor(it)
            }
        }
    }


    fun convertListToMap(list: List<Camera>) {
        list.forEach {
            if (this.camerasList.value?.contains(it.room) == true) {
                this.camerasList.value!![it.room]!!.add(it)
            } else {
                var room = "UNDEFINED ROOM"
                if (it.room != null) {
                    room = it.room!!
                }
                this.camerasList.value?.put(room, mutableListOf<Camera>())
                this.camerasList.value!![room]!!.add(it)
            }
        }
    }
}