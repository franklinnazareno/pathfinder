package com.example.nazarenopathfinder

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    @WorkerThread
    suspend fun register(user: User) {
        userDao.register(user)
    }

    @WorkerThread
    suspend fun login(email: String): User? {
        return try {
            userDao.login(email)
        } catch (e: NoSuchElementException) {
            null
        }
    }

}