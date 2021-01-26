package com.azamat.youtube.models.youtube

import androidx.room.TypeConverters
import com.azamat.youtube.utils.Converters

data class Thumbnails(
    val default: Default,
    val high: High,
    val maxres: Maxrez,
@TypeConverters(Converters::class)
    val medium: Medium,
    val standard: Standart
)