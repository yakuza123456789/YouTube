package com.azamat.youtube.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.azamat.youtube.models.youtube.Item

@Dao
interface YouTubeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDetailsList(list: MutableList<Item>)

    @Query("SELECT * FROM videos")
    fun getDetailslist(): MutableList<Item>

}