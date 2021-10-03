package com.example.android.multithreading.java.interrupt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.multithreading.R
import kotlin.math.sin
import kotlin.random.Random

class ThreadInterruptActivity : AppCompatActivity() {

    companion object{
        const val TAG = "ThreadInterruptActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_thread_interrupt)


        Log.d(TAG,"started")
        val t1 = Thread{

            for(i in 0 until 1E6.toInt()){

                /*if (Thread.currentThread().isInterrupted){
                    Log.d(TAG,"interrupted!")
                    break
                }*/

                    try {
                        Thread.sleep(1)
                    }catch (e:InterruptedException){
                        break
                    }

                sin(Random.nextDouble())
            }
        }
        t1.start()

        Thread.sleep(1000)
        t1.interrupt()

        t1.join()
        Log.d(TAG,"finished")
    }
}