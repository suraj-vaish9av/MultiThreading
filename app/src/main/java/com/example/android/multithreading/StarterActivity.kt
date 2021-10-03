package com.example.android.multithreading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android.multithreading.handlerAndThreads.MainActivity
import com.example.android.multithreading.java.callableAndFuture.CallableAndFutureActivity
import com.example.android.multithreading.java.deadlock.DeadlockActivity
import com.example.android.multithreading.java.interrupt.ThreadInterruptActivity
import com.example.android.multithreading.java.latches.CountDownLatchesImplActivity
import com.example.android.multithreading.java.producerConsumer.ProducerConsumerActivity
import com.example.android.multithreading.java.producerConsumerViaWaitNotify.ProducerConsumerActivityImpl
import com.example.android.multithreading.java.reentrant.ReentrantLockActivity
import com.example.android.multithreading.java.synchro.multiLock.MultiLockActivity
import com.example.android.multithreading.java.synchro.syncBlock.SynchronousImplActivity
import com.example.android.multithreading.java.synchro.volat.ThreadSynchronizationActivity
import com.example.android.multithreading.java.threadPool.ThreadPoolExecutorServiceActivity
import com.example.android.multithreading.java.threadTypes.JavaThreadsImplActivity

class StarterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)
    }

    fun handlerAndThreads(view: View) {
        startActivity<MainActivity>()
    }

    fun threadTypes(view: View) {
        startActivity<JavaThreadsImplActivity>()
    }

    fun syncVolatile(view: View) {
        startActivity<ThreadSynchronizationActivity>()
    }

    fun syncBlock(view: View) {
        startActivity<SynchronousImplActivity>()
    }

    fun multiLock(view: View) {
        startActivity<MultiLockActivity>()
    }

    fun threadPoolExecutors(view: View) {
        startActivity<ThreadPoolExecutorServiceActivity>()
    }

    fun countDownLatch(view: View) {
        startActivity<CountDownLatchesImplActivity>()
    }

    fun producerConsumer(view: View) {
        startActivity<ProducerConsumerActivity>()
    }

    fun producerConsumerWithWaitAndNotify(view: View) {
        startActivity<ProducerConsumerActivityImpl>()
    }

    fun reentrantLock(view: View) {
        startActivity<ReentrantLockActivity>()
    }

    fun deadLock(view: View) {
        startActivity<DeadlockActivity>()
    }

    fun callableAndFuture(view: View) {
        startActivity<CallableAndFutureActivity>()
    }

    fun interrupt(view: View) {
        startActivity<ThreadInterruptActivity>()
    }
}