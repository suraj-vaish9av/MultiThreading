package com.example.android.multithreading.java.callableAndFuture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.android.multithreading.R
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.random.Random

class CallableAndFutureActivity : AppCompatActivity() {

    companion object{
        const val TAG = "CallableActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_callable_and_future)
    }

    fun startCallable(view: View) {
        val executorService = Executors.newCachedThreadPool()

        // We can use Callable<Void> to not return anything, in case when we want to use some methods of future
        // but not want to return anything
        val future = executorService.submit(Callable<Long>{

            val randomWait = Random.nextLong(5000)+1000

            Log.d(TAG,"started")

            if (randomWait>2000)
                throw IllegalStateException("too long to process")

            Thread.sleep(randomWait)

            Log.d(TAG,"finished")

            return@Callable randomWait
        })

        try {
            val result = future.get()
            Log.d(TAG, "result: $result")
        }catch (e:Exception){
            Log.e(TAG, e.toString())
        }
    }
}