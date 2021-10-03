package com.example.android.multithreading.handlerAndThreads

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.widget.Toast

class MyHandlerThread(val context: Context) : HandlerThread(MyHandlerThread::class.simpleName) {

    lateinit var handler : Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()

        handler= Handler(looper){

            Toast.makeText(context, "hi from MyHandlerThread", Toast.LENGTH_SHORT).show()

            return@Handler false
        }

        handler.post {
            Toast.makeText(context, "hi from MyHandlerThread", Toast.LENGTH_SHORT).show()
        }
    }

    /*fun postRunnable(runnable: Runnable){
        handler.post(runnable)
    }*/
}