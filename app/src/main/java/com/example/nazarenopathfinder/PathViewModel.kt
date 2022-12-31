package com.example.nazarenopathfinder

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.util.*

class PathViewModel(private val repository: PathItemRepository): ViewModel() {

    var pathItems: LiveData<List<PathItem>> = repository.allPathItems.asLiveData()

    fun addPathItem(newPath: PathItem) = viewModelScope.launch {
        repository.insertPathItem(newPath)
    }

    fun updatePathItem(pathItem: PathItem) = viewModelScope.launch {
        repository.updatePathItem(pathItem)
    }

    fun deletePathItem(pathItem: PathItem) = viewModelScope.launch {
        repository.deletePathItem(pathItem)
    }

    fun deleteAllPathItems() = viewModelScope.launch {
        repository.deleteAllPathItems()
    }
}

class PathItemModelFactory(private val repository: PathItemRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PathViewModel::class.java)) {
            return PathViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class for view model")
    }
}