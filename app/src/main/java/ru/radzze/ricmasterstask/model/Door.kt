package ru.radzze.ricmasterstask.model

import kotlinx.serialization.Serializable

@Serializable
data class Door(
    val id:Int,
    val name:String,
    val room:String?,
    val favorites:Boolean,
    val snapshot:String? = null
)
