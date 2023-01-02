package com.example.nazarenopathfinder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, PathItem::class], version = 1, exportSchema = false)
abstract class PathFinderDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun pathItemDao(): PathItemDao

    companion object {
        @Volatile
        private var INSTANCE: PathFinderDatabase? = null

        fun getDatabase(context: Context): PathFinderDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PathFinderDatabase::class.java,
                    "path_finder_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}