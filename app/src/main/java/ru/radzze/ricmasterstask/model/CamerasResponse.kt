package ru.radzze.ricmasterstask.model

import kotlinx.serialization.Serializable

@Serializable
data class CamerasResponse(
    val success:Boolean,
    val data:CamerasData
)
