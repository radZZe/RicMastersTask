package ru.radzze.ricmasterstask.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
class Camera:RealmObject{
    @PrimaryKey
    var id:Int = 0
    var name:String = ""
    var snapshot:String = ""
    var room:String? = null
    var favorites:Boolean =false
    var rec:Boolean = false
}
