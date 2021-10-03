package com.example.android.multithreading.java.latches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.multithreading.R
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

// countDownLatch is a thread safe class so we don't need to use synchronization here
class CountDownLatchesImplActivity : AppCompatActivity() {

    companion object{
        const val TAG = "CountDown"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down_latches_impl)

        // this will set initial value here we are setting it to 3
        val countDownLatch = CountDownLatch(3)

        // executor will create 2 threads and reuse them
        val executors = Executors.newFixedThreadPool(2)

        // we are submitting 3 task to executors
        repeat(3){
            executors.submit(Processor(it,countDownLatch))
        }

        //this will await until counter of count down latches reaches to 0
        countDownLatch.await()
        Log.d(TAG,"All task completed")
    }

    class Processor(private val id:Int, private val countDownLatch: CountDownLatch) : Runnable{
        override fun run() {
            Log.d(TAG, "started $id")

            Thread.sleep(200)

            // decrement the count by 1
            countDownLatch.countDown()

            Log.d(TAG, "ended $id")
        }
    }
}