package com.example.android.multithreading.java.producerConsumerViaWaitNotify

import android.util.Log

/**
 * This class just shows the use of wait and notify method
 */
class Processor : Object() {

    // Note: wait and notify are defined in Object class of Java
    // wait and notify can only be called from synchronized method or block
    // if wait and notify are related to each other they should use same synchronized lock object, like here we used this keyword in producer and consumer

    companion object{
        const val TAG = "Processor"
    }

    fun producer(){
        synchronized(this){ // this means Processor object, so we are apply lock on Processor Object
            Log.d(TAG,"producer started")
            // the control of synchronized block pauses here and wait until other thread which has the lock calls notify or notifyAll
            // and later their synchronized block executed
            wait()
            Log.d(TAG,"producer resumed")
        }
    }

    fun consumer(){
        synchronized(this){     // here we have applied lock on same object that is; Processor Object
            Log.d(TAG,"consumer started")
            Thread.sleep(3000)
            notify()    // notify will notify a random thread to resume its execution, here we have only one thread so no worry
            //notifyAll() // it notifies all thread and we have to manage it to which thread should starts execution, we can do it via a flag
        }
    }
}