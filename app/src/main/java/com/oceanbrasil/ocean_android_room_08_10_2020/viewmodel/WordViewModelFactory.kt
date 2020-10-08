package com.oceanbrasil.ocean_android_room_08_10_2020.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WordViewModelFactory(val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = WordViewModel(application) as T
}
