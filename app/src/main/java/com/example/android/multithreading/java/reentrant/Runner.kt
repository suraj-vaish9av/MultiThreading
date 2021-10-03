package com.example.android.multithreading.java.reentrant

import android.util.Log
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock

class Runner {

    companion object{
        const val TAG = "Runner"
    }

    var counter = 0
    // with reentrant lock we can apply lock for n number of times then we have to unlock n number of times
    private val lock = ReentrantLock()
    val cond: Condition = lock.newCondition()

    private fun increment(){
        repeat(1000){
            counter++
        }
    }

    fun printCounter(){
        Log.d(TAG, "counter: $counter")
    }

    fun firstThread(){
        lock.lock()     // applies the lock via reentrant lock object

        Log.d(TAG,"first thread waiting")
        // pauses the execution it is same like the wait method we used with synchronized block
        // following statements executes when cond.signal() is called
        cond.await()


        Log.d(TAG,"first thread woken up")
        try {
            increment()
        }finally {
            // to unlock just call unlock method
            // note that it is important to put it in finally block because our code may throw some exception
            // and if it does the following unlock method won't be called, if lock is not release other thread which uses the same lock cannot executes
            lock.unlock()
        }
    }

    fun secondThread(){
        lock.lock()
        Log.d(TAG,"second thread started")
        try {
            increment()
        }finally {
            cond.signal()   // it is same like notify, when this method is called the other thread can resume the execution of statements after cond.await
            lock.unlock()

            Log.d(TAG,"second thread completed")
        }
    }
}