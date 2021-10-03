package com.example.android.multithreading.java.reentrant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.multithreading.R

class ReentrantLockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reentrant_lock)

        val runner = Runner()

        val t1 = Thread{
            runner.firstThread()
        }

        val t2 = Thread{
            runner.secondThread()
        }

        t1.start()
        t2.start()

        t1.join()
        t2.join()

        runner.printCounter()
    }
}