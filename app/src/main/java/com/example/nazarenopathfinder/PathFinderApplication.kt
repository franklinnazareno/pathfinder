package com.example.nazarenopathfinder

import android.app.Application

class PathFinderApplication: Application() {
    private val database by lazy { PathFinderDatabase.getDatabase(this) }
    val pathItemRepository by lazy { PathItemRepository(database.pathItemDao()) }
    val userRepository by lazy { UserRepository(database.userDao()) }
}