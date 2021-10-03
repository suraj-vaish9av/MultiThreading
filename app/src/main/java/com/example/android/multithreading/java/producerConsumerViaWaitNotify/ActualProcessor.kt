package com.example.android.multithreading.java.producerConsumerViaWaitNotify

import android.util.Log
import java.util.*
import kotlin.random.Random

class ActualProcessor {

    companion object{
        const val LIMIT = 10
        const val TAG="ActualProcessor"
    }

    private val list = LinkedList<Int>()
    // we are using separate lock object here we can also use ActualProcessor reference (via this keyword)
    // but to understand how wait and notify work we are using this lock object
    private val lock = Object()

    private var value = 0


    fun producer(){
        while (true){
            synchronized(lock){
                while (list.size==LIMIT)
                {
                    lock.wait()     // wait if we reached the limit
                }   // we could have used if condition here but after resuming we want to make sure that we are not reaching the limit,
                // so because of loop condition will be checked again


                list.add(value++)
                lock.notify()   // this notify is for consumer's wait
            }
        }
    }

    fun consumer(){
        while (true){
            synchronized(lock){
                while (list.size==0){
                    lock.wait()     // wait if no element in the list
                }

                val value = list.removeFirst()
                Log.d(TAG, "value: $value")

                Thread.sleep(Random.nextInt(1000).toLong())

                lock.notify()   // notifies producer's wait
            }
        }
    }
}