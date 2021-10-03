package com.example.android.multithreading.java.threadTypes

import android.util.Log

class ThreadRunnable : Runnable {
    override fun run() {
        repeat(10){
            Log.d("ThreadExt",it.toString())
        }
    }
}

