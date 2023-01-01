package com.example.nazarenopathfinder

import androidx.lifecycle.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel() {
    fun register(newUser: User) = viewModelScope.launch {
        repository.register(newUser)
    }
    suspend fun login(email: String): User? = viewModelScope.async {
        return@async repository.login(email)
    }.await()

}

class UserModelFactory(private val repository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Class for View Model")
    }
}