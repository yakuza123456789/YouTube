package com.azamat.youtube.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.azamat.youtube.models.youtube.Item
import com.azamat.youtube.utils.Converters


@Database(
    entities = [Item::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class YouTubeDataBase : RoomDatabase() {

    abstract fun youTubedao(): YouTubeDao

    companion object {
        @Volatile
        private var INSTANCE: YouTubeDataBase? = null

        fun getDatabase(context: Context): YouTubeDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    YouTubeDataBase::class.java,
                    "youtube_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance

                instance
            }
        }

    }

}