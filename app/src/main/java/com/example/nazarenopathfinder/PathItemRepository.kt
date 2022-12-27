package com.example.nazarenopathfinder

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class PathItemRepository(private val pathItemDao: PathItemDao) {
    val allPathItems: Flow<List<PathItem>> = pathItemDao.allPathItems()

    @WorkerThread
    suspend fun insertPathItem(pathItem: PathItem) {
        pathItemDao.insertPathItem(pathItem)
    }

    @WorkerThread
    suspend fun updatePathItem(pathItem: PathItem) {
        pathItemDao.updatePathItem(pathItem)
    }
}