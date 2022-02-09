package com.naim.livedatavsflow.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.naim.livedatavsflow.MainActivity
import com.naim.livedatavsflow.databinding.ActivityGeneralBinding
import com.naim.livedatavsflow.viewmodel.GeneralViewModel
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GeneralActivity : AppCompatActivity() {
    private var binding: ActivityGeneralBinding? = null
    private val viewModel: GeneralViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGeneralBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.button?.setOnClickListener {
//            viewModel.setGotoNextActivitySharedFlow(true)
            viewModel.setGotoNextActivitySharedFlow(true)
        }
        binding?.button2?.setOnClickListener {
//            viewModel.setGotoNextActivity(true)
//            binding?.textView?.text = "Hello"
            Intent(this@GeneralActivity, MainActivity::class.java).apply { startActivity(this) }
        }


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
        lifecycleScope.launchWhenCreated {
            viewModel.titleSharedFlow.collect {
                println("Resume flow")
                binding?.textView?.text = it
            }
        }
        viewModel.title.observe(this) {
            println("Resume live $it")
            binding?.textView?.text = it
        }
        lifecycleScope.launchWhenCreated {
            viewModel.titleStateFlow.collect {
                println("Resume state")
                binding?.textView?.text = it
            }
        }

    }
}