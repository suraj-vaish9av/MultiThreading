package com.example.android.multithreading.java.synchro.multiLock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.multithreading.R
import kotlin.random.Random
import kotlin.system.measureTimeMillis

// In this class we have used multiple synchronized locks
// Task: What we want is we want to fill two list with some random value from two different methods: stageOne and stageTwo.
// we have created two thread t1,t2 and filling data in both lists from both threads.
// but the problem is, if we use synchronized block with MultiLockActivity means that will use intrinsic lock for MultiLockActivity object
// on both method stageOne and stageTwo. now if t1 is running stageOne means t1 has the lock, and at that time t2 cannot run stageOne method
// that is ok, but t2 also cannot run stageTwo method, because same lock used in both methods as t2 tries to run stageTwo but that is the same lock
// used for stageOne which is currently acquired by t1, as soon as t1 releases the lock then t2 can run stageOne or stageTwo method.
// but stageOne and stageTwo are not related to each other they could be run simultaneously by different threads. to do that
// we need to use different lock for stageOne and stageTwo
class MultiLockActivity : AppCompatActivity() {

    // we can apply lock on list1 or list2 and that will work, but suppose we have some integer variable and we use lock on that
    // and we don't know when java does the optimization and due to same values it can use same reference for multiple variables
    // so to avoid these kind of confusion just use separate objects for locking.
    private val lock1 = Any()
    private val lock2 = Any()

    private val list1= mutableListOf<Int>()
    private val list2= mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_lock)

        val timeTaken = measureTimeMillis {
            val t1 = Thread{
                startProcess()
            }

            val t2 = Thread{
                startProcess()
            }

            t1.start()
            t2.start()

            t1.join()
            t2.join()
        }
        Log.d("timeTaken",timeTaken.toString())
    }

    private fun startProcess(){
        repeat(1000){
            stageOne()
            stageTwo()
        }
    }


    private fun stageOne(){
        synchronized(lock1){    // applied lock on lock1
            Thread.sleep(1)
            list1.add(Random.nextInt(100))
        }
    }

    private fun stageTwo(){
        synchronized(lock2){    // applied lock on lock1
            Thread.sleep(1)
            list2.add(Random.nextInt(100))
        }
    }
}