package com.example.android.multithreading.java.threadPool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.multithreading.R
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadPoolExecutorServiceActivity : AppCompatActivity() {

    companion object{
        const val TAG = "ThreadPool"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_pool_excutor_service)

        // here Executors will create 2 threads
        val executorService = Executors.newFixedThreadPool(2)

        // and executes these 5 submitted task, so initially both threads starts with task0 and task1
        // as id we are passing is integer starting from 0
        // once a task is completed by one thread it will start another task if there is any
        // that means here we are creating few threads to execute multiple task all by means that we are reusing threads.
        repeat(5){
            executorService.submit(Processor(it))
        }
        executorService.shutdown()  // it will not immediately shutdown the executor

        Log.d(TAG,"all task submitted")

        // the following statements will be executed after the termination of executors means executors complete all its tasks
        executorService.awaitTermination(3000,TimeUnit.MILLISECONDS)
        Log.d(TAG,"all task completed")
    }

    class Processor(private val id:Int) : Runnable{
        override fun run() {
            Log.d(TAG,"Processor started: $id")

            Thread.sleep(500)

            Log.d(TAG,"Processor ended: $id")
        }
    }
}