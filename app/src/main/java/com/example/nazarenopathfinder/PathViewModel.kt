package com.example.nazarenopathfinder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PathViewModel: ViewModel() {
    var name = MutableLiveData<String>()
    var source = MutableLiveData<String>()
    var destination = MutableLiveData<String>()
    var description = MutableLiveData<String>()
}