package com.example.android.multithreading.java.synchro.syncBlock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.multithreading.R

class SynchronousImplActivity : AppCompatActivity() {

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_synchronous_impr)

        doWork()
        evenOddCounter()
    }

    @Synchronized private fun increment(){
        counter++
    }

    var evenOddCounter = 0
    @Synchronized private fun incrementEvenOddCounter(): Boolean {
        if (evenOddCounter<1000){
            evenOddCounter++
            Log.d("evenOddCounter",evenOddCounter.toString())
            return true
        }
        return false
    }

    private fun evenOddCounter(){
        val t1 = Thread{
            var keepRunning = true
            while (keepRunning)
            {
                keepRunning = incrementEvenOddCounter()
            }
        }

        val t2 = Thread{
            var keepRunning = true
            while (keepRunning)
            {
                keepRunning = incrementEvenOddCounter()
            }
        }

        t1.start()
        t2.start()
    }

    private fun doWork(){
        val t1 = Thread {
            repeat(1000){
                increment()
            }
        }

        val t2 = Thread {
            repeat(1000){
                increment()
            }
        }

        t1.start()
        t2.start()

        // just waiting for threads to die won't gives us the expected result as threads runs in parallel
        // so even if we are only incrementing one increment operation from thread 1 can override the increment operation from thread 2
        // so we need to synchronize increment operation
        t1.join()   // wait for thread to die
        t2.join()   // wait for thread to die
        Log.d("counter",counter.toString())
    }
}