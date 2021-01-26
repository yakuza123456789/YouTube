package com.azamat.youtube.models.youtube

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.azamat.youtube.utils.Converters
import org.koin.core.definition.Kind
import java.io.Serializable

@Entity(tableName = "videos")
data class Item(
    @PrimaryKey
    val id: String,
    @TypeConverters(Converters::class)
    val contentDetails: ContentDetails,
    val tag: String,
    val kind: String,
    @TypeConverters(Converters::class)
    val snippet: Snippet


):Serializable