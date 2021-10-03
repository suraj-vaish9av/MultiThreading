package com.example.android.multithreading.java.threadTypes

import android.util.Log

class ThreadExt : Thread() {

    override fun run() {
        super.run()
        repeat(10){
            Log.d("ThreadExt",it.toString())
        }
    }
}