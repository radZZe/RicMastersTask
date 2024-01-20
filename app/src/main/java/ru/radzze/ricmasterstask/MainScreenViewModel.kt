package ru.radzze.ricmasterstask

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.radzze.ricmasterstask.data.Repositories.DoorsRepository
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    @ApplicationContext application: Context,
    private val doorRep:DoorsRepository
) : ViewModel() {
    val tabList: List<String> =
        listOf(application.getString(R.string.cameras), application.getString(R.string.doors))
    var selectedTabIndex by mutableStateOf(0)

    fun jopa(){
        viewModelScope.launch(Dispatchers.IO) {
           val jopa =  doorRep.getDoors()
            val test = 5
        }

    }
}