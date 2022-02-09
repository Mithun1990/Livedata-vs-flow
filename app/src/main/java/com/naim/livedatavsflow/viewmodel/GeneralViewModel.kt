package com.naim.livedatavsflow.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class GeneralViewModel : ViewModel() {
    private var _gotoNextActivity = MutableLiveData<Boolean>(false)
    private var _title = MutableLiveData<String>("P")
    private var _gotoNextActivitySharedFlow = MutableSharedFlow<Boolean>(replay = 2)
    private var _titleSharedFlow = MutableSharedFlow<String>()
    private var _titleStateFlow = MutableStateFlow<String>("A")
    val gotoNextActivity = _gotoNextActivity
    val title = _title
    val titleStateFlow = _titleStateFlow
    val titleSharedFlow = _titleSharedFlow
    val gotoNextActivitySharedFlow = _gotoNextActivitySharedFlow.asSharedFlow()
    fun setGotoNextActivity(value: Boolean) {
//        _gotoNextActivity.value = value
        viewModelScope.launch {
            delay(10000)
            _title.value = "Hello"
            _titleSharedFlow.emit("Hello data")
            _titleStateFlow.value = "Hello"
        }
    }

    fun setGotoNextActivitySharedFlow(value: Boolean) {
        viewModelScope.launch {
            delay(10000)
            _title.value = "Hello"
            _titleSharedFlow.emit("Hello data")
            _titleStateFlow.value = "Hello"
        }
    }

    init {
        viewModelScope.launch {
            delay(10000)
            _title.value = "Hello"
            _titleSharedFlow.emit("Hello data")

        }
    }

}