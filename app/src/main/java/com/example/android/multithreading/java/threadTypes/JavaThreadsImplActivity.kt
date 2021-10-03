package com.example.android.multithreading.java.threadTypes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.multithreading.R

class JavaThreadsImplActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_java_threads_impl)

        startThreadWhichIsExtendedByThreadClass()
        startThreadWhichIsImplementingRunnable()
    }

    private fun startThreadWhichIsExtendedByThreadClass(){
        val t1 = ThreadExt()
        val t2 = ThreadExt()
        t1.start()
        t2.start()
    }

    private fun startThreadWhichIsImplementingRunnable(){
        val t1 = Thread(ThreadRunnable())
        val t2 = Thread(ThreadRunnable())
        t1.start()
        t2.start()
    }
}