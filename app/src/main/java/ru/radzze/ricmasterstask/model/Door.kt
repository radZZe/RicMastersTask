package ru.radzze.ricmasterstask.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
class Door:RealmObject{
    @PrimaryKey
    var id:Int = 0
    var name:String = ""
    var room:String? = null
    var favorites:Boolean = false
    var snapshot:String? = null
}
