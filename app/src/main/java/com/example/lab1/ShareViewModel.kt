package com.example.lab1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val fromCity = MutableLiveData<String>()
    val toCity = MutableLiveData<String>()
    val time = MutableLiveData<String>()

    fun clearData() {
        fromCity.value = ""
        toCity.value = ""
        time.value = ""
    }
}
