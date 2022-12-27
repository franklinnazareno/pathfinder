package com.example.nazarenopathfinder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class PathViewModel: ViewModel() {
    var pathItems = MutableLiveData<MutableList<PathItem>>()

    init {
        pathItems.value = mutableListOf()
    }

    fun addPathItem(newPath: PathItem) {
        val list = pathItems.value
        list!!.add(newPath)
        pathItems.postValue(list)
    }

    fun updatePathItem(id: UUID, name: String, source: String, destination: String, description: String) {
        val list = pathItems.value
        val path = list!!.find { it.id == id }!!
        path.name = name
        path.source = source
        path.destination = destination
        path.description = description
        pathItems.postValue(list)
    }


}