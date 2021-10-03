package com.example.android.multithreading.java.synchro.volat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.multithreading.databinding.ActivityThreadSynchronizationBinding

class ThreadSynchronizationActivity : AppCompatActivity() {

    private val binding by lazy { ActivityThreadSynchronizationBinding.inflate(layoutInflater) }
    private val threadWithVolatile by lazy { ThreadWithVolatile() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnStartThread.setOnClickListener {
            threadWithVolatile.start()
        }

        binding.btnEndThread.setOnClickListener {
            threadWithVolatile.shutdown()
        }
    }
}