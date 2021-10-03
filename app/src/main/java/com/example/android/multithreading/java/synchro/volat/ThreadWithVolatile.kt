package com.example.android.multithreading.java.synchro.volat

import android.util.Log

class ThreadWithVolatile : Thread() {

    // We are running a thread until it is singled to shutdown
    // well this may work in most of the system but in some system
    // a thread could cache the value of the variable, means we see this code,
    // in the run method that's actually runs in background by this thread and is not calling shutdown method
    // or not changing the value of isRunning in any way. So it can cache the default value of isRunning which true,
    // and even if we change the value of isRunning it the loop won't stop because the value was already cached.

    // This is statement can also be said like that: isRunning variable is shared between two or multiple threads,
    // one by the ThreadWithVolatile itself who is reading this variable in run method and by other thread who can call shutdown method
    // so they both can manage the local copy of isRunning

    //private var isRunning = true

    // Whats is the solution:
    // use volatile keyword with isRunning that's prevent multiple copy of that variable or we can say caching of the variable.

    @Volatile private var isRunning = true
    override fun run() {
        super.run()
        while(isRunning){
            Log.d("ThreadWithVolatile","Hello from thread")
            sleep(100)
        }
    }

    fun shutdown(){
        isRunning=false
    }
}