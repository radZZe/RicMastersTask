package ru.radzze.ricmasterstask.model

import kotlinx.serialization.Serializable

@Serializable
data class CamerasData(
    val room:List<String>,
    val cameras:List<Camera>
)
