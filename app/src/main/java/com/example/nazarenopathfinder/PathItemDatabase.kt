package com.example.nazarenopathfinder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PathItem::class], version = 1, exportSchema = false)
abstract class PathItemDatabase: RoomDatabase() {
    abstract fun pathItemDao(): PathItemDao

    companion object {
        @Volatile
        private var INSTANCE: PathItemDatabase? = null

        fun getDatabase(context: Context): PathItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PathItemDatabase::class.java,
                    "path_item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}