package ru.radzze.ricmasterstask.model

import kotlinx.serialization.Serializable

@Serializable
data class DoorsResponse(
    val success:Boolean,
    val data:List<Door>
)
