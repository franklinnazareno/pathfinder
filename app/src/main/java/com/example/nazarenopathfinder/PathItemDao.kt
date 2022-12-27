package com.example.nazarenopathfinder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PathItemDao {
    @Query("SELECT * FROM path_item_table ORDER BY id ASC")
    fun allPathItems(): Flow<List<PathItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPathItem(pathItem: PathItem)

    @Update
    suspend fun updatePathItem(pathItem: PathItem)

    @Delete
    suspend fun deletePathItem(pathItem: PathItem)
}