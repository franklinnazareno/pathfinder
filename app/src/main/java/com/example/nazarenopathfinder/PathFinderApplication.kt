package com.example.nazarenopathfinder

import android.app.Application

class PathFinderApplication: Application() {
    private val database by lazy { PathItemDatabase.getDatabase(this) }
    val repository by lazy { PathItemRepository(database.pathItemDao()) }
}