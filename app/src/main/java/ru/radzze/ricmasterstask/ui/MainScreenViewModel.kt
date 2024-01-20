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
import kotlinx.coroutines.launch
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
    private val doorRep:DoorsRepository,
    private val cameraRep:CamerasRepository,
    private val dbRep:RealmRepository
) : ViewModel() {
    val tabList: List<String> =
        listOf(application.getString(R.string.cameras), application.getString(R.string.doors))
    var selectedTabIndex by mutableStateOf(0)
    var camerasList:MutableLiveData<List<Camera>> =MutableLiveData<List<Camera>>()
    var doorsList:MutableLiveData<List<Door>> =MutableLiveData<List<Door>>()

    init{
        viewModelScope.launch {
            dbRep.getDoors().collect{
                doorsList.value = it

            }
            dbRep.getCameras().collect{
                camerasList.value = it
            }
        }
    }

    fun jopa(){
        val test = 5
        viewModelScope.launch(Dispatchers.IO) {
           val jopa =  doorRep.getDoors()
            val test = 5
        }

    }

    fun jopa1(){
        viewModelScope.launch(Dispatchers.IO) {
            val jopa =  cameraRep.getCameras()
            val test = 5
        }

    }
}