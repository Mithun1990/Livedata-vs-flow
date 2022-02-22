package com.naim.livedatavsflow.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.naim.livedatavsflow.MyApplication
import com.naim.livedatavsflow.databinding.ActivityGeneralBinding
import com.naim.livedatavsflow.model.Book
import com.naim.livedatavsflow.room.AppDatabase
import com.naim.livedatavsflow.viewmodel.GeneralViewModel
import com.naim.livedatavsflow.viewmodel.factory.GeneralViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class GeneralActivity : AppCompatActivity() {
    private var binding: ActivityGeneralBinding? = null
    private var isData: String = "A"
    private val viewModel: GeneralViewModel by viewModels() {
        GeneralViewModelFactory(
            AppDatabase.getDatabase(
                this
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneralBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.button?.setOnClickListener {
            lifecycleScope.launchWhenCreated {
                viewModel.titleStateFlow.collect {
                    println("Resume flow $it")
                    binding?.textView?.text = it
                }
            }
//            viewModel.setGotoNextActivitySharedFlow(true)
//            viewModel.setGotoNextActivitySharedFlow(true)
//            lifecycleScope.launch {
//                viewModel.countDownTime.collect {
//                    binding?.textView?.text = "$it"
//                }
//            }
        }
        binding?.button2?.setOnClickListener {
            viewModel.setBook(
                Book(
                    bookName = System.currentTimeMillis().toString() + "A",
                    authorName = "122B"
                )
            )
//            viewModel.setGotoNextActivitySharedFlow(false)
//            lifecycleScope.launch {
//                viewModel.countDownTime.collect {
//                    binding?.textView?.text = "$it"
//                }
//            }
//            isData = "B"
//            println("Count down B added")
        }

//        lifecycleScope.launchWhenCreated {
//            viewModel.titleSharedFlow.collect {
//                println("Resume flow $it")
//                binding?.textView?.text = it
//            }
//        }
//        viewModel.title.observe(this) {
//            println("Resume live $it")
//            binding?.textView?.text = it
//        }
//        lifecycleScope.launchWhenCreated {
//            viewModel.titleStateFlow.collect {
//                println("Resume state $it")
//                binding?.textView?.text = it
//            }
//        }
        viewModel.getBookList().observe(this) {
            println("Value live  ${it.size}")
        }
        lifecycleScope.launchWhenCreated {
            viewModel.getBookListFlow().collect {
                println("Value flow  ${it.size}")
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            repeat(120000) {
                delay(200)
                println("Count down ${Thread.currentThread()} ${isData}")
            }
        }

        lifecycleScope.launch(Dispatchers.Default) {
            repeat(120000) {
                delay(100)
                println("Count down ${Thread.currentThread()} ${isData}")
            }
        }


//        thread {
//            while (true) {
//                Thread.sleep(100)
//                println("Count down 2 ${Thread.currentThread()} ${isData}")
//            }
//        }

//        Thread.sleep(30000)

    }

    override fun onResume() {
        super.onResume()
        println("Resume")
//        viewModel.gotoNextActivity.observe(this) {
//            println("Live data called $it")
//            if (it) {
//                binding?.textView?.text = "Hello"
//                Intent(this@GeneralActivity, MainActivity::class.java).apply { startActivity(this) }
//            }
//        }


    }
}