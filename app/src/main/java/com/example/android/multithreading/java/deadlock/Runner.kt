package com.example.android.multithreading.java.deadlock

import android.util.Log
import java.util.concurrent.locks.ReentrantLock
import kotlin.random.Random

class Runner {

    companion object{
        const val TAG = "RunnerAccount"
    }

    private val account1 = Account()
    private val account2 = Account()

    // we can use synchronized lock but we are working on two objects means two accounts so we need to locks
    // this can be done via synchronized but we need to do nesting here
    // so let's use two reentrant locks
    private val lock1 = ReentrantLock()
    private val lock2 = ReentrantLock()


    // this method is important
    // this help us to refrain from occurrence of deadlock
    fun acquireLock(){
        var gotLock1 = false
        var gotLock2 = false

        while (true){
            try {
                gotLock1 = lock1.tryLock()
                gotLock2 = lock2.tryLock()
            }finally {

                if (gotLock1 && gotLock2)
                    return

                if (gotLock1)
                    lock1.unlock()

                if (gotLock2)
                    lock2.unlock()
            }
        }
    }

    fun firstThread(){
        repeat(1000){
            // note these two following commented stmt
            // first lock1 locks then second lock locks
            // check secondThread there, lock2 locks then lock1 locks
            // now the problem with this is: in firstThread lock1.lock calls and from the secondThread lock2.lock calls
            // later following stmt in firstThread lock2.lock calls but it cannot acquire the lock because that is already acquired by secondThread method
            // similarly in secondThread following stmt is lock1.lock, but that can not acquire lock because the lock is already acquired by firstThread method
            // This is the deadlock, here order of the lock is important, if we do not follow same order of locks a deadlock can occur
            /*lock1.lock()
            lock2.lock()*/

            // So to resolve deadlock there can be two solutions:
            // 1. use same orders of lock
            // 2. use the tryLock method of reentrant lock (which we did in our acquireLock method)
            acquireLock()
            try {
                Account.transfer(account1,account2,Random.nextInt(100))
            }finally {
                lock1.unlock()
                lock2.unlock()
            }

        }
    }

    fun secondThread(){
        repeat(1000){
            /*
            lock2.lock()
            lock1.lock()
            */
            acquireLock()
            try {
                Account.transfer(account2,account1,Random.nextInt(100))
            }finally {
                lock1.unlock()
                lock2.unlock()
            }
        }
    }

    fun printBalance(){
        Log.d(TAG,"account1 balance: "+account1.balance)
        Log.d(TAG,"account2 balance: "+account2.balance)
        Log.d(TAG,"total account balance: "+(account1.balance+account2.balance))
    }
}