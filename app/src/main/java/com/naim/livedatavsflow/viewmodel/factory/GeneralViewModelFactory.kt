package com.naim.livedatavsflow.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naim.livedatavsflow.room.AppDatabase
import com.naim.livedatavsflow.viewmodel.GeneralViewModel
import java.lang.IllegalArgumentException

class GeneralViewModelFactory(private val appDatabase: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GeneralViewModel::class.java)){
            return GeneralViewModel(appDatabase) as T
        }
        throw IllegalArgumentException("Unknown class cast exception")
    }
}