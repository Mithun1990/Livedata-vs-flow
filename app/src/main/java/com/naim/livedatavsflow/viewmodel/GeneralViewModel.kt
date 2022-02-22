package com.naim.livedatavsflow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naim.livedatavsflow.model.Book
import com.naim.livedatavsflow.room.AppDatabase
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GeneralViewModel(private val appDatabase: AppDatabase) : ViewModel() {
    private var _gotoNextActivity = MutableLiveData<Boolean>(false)
    private var _title = MutableLiveData<String>("P")
    private var _gotoNextActivitySharedFlow = MutableSharedFlow<Boolean>(replay = 2)
    private var _titleSharedFlow = MutableSharedFlow<String>()
    private var _titleStateFlow = MutableStateFlow<String>("A")
    private var _bookList: MutableLiveData<List<Book>> = MutableLiveData<List<Book>>()
    val bookList = _bookList
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
//            delay(10000)
            _title.value = "Hello"
            _titleSharedFlow.emit(System.currentTimeMillis().toString())
            _titleStateFlow.value = System.currentTimeMillis().toString()
        }
    }

    fun getBookList(): LiveData<List<Book>> {
        return appDatabase.getBookDao().getAll()
    }

    fun getBookListFlow(): Flow<List<Book>> {
        return appDatabase.getBookDao().getAllNew()
    }

    fun setBook(book: Book) {
        viewModelScope.launch { appDatabase.getBookDao().add(book) }
    }

    init {
        viewModelScope.launch {
            delay(10000)
            _title.value = "Hello"
            _titleSharedFlow.emit("Hello data")

        }
    }

    val countDownTime: Flow<Int> = flow {
        repeat(100) {
            delay(1000)
            println("Emit $it")
            emit(it)
        }
    }
}