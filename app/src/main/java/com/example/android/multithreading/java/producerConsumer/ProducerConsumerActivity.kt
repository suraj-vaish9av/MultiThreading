package com.example.android.multithreading.java.producerConsumer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.multithreading.R
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import kotlin.random.Random

//Here we are implemented producer and consumer approach via two threads using blocking queue
// t1 as producer, and t2 as consumer there could me multiple producer and consumer
// producer insert items in blocking and consumer take it from blocking queue
class ProducerConsumerActivity : AppCompatActivity() {

    companion object{
        const val TAG= "ProducerConsumerAct"
    }

    // blocking queue is thread safe, means that we can use this in multiple threads without worrying about locks
    // note that we have given the size of blocking queue is 10
    private val blockingQueue:BlockingQueue<Int> = ArrayBlockingQueue<Int>(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producer_consumer)

        val t1 = Thread{
            producer()
        }

        val t2 = Thread{
            consumer()
        }

        t1.start()
        t2.start()

        try {
          //  t1.join()
          //  t2.join()
        }catch (e:InterruptedException){
            Log.e(TAG,e.toString())
        }

    }

    private fun producer(){
        while (true){
            val value = Random.nextInt(100)
            // this will insert a new element in queue
            // if blocking queue is full then it will wait for blocking queue to have at least free space for one element
            // as soon as a free space found this will insert the item in blockingQueue
            blockingQueue.put(value)
        }
    }

    private fun consumer(){
        while (true){
            Thread.sleep(100)
            if (Random.nextInt(10)==0)
            {
                // blocking queue take method will return a value from front and remove it, so blocking queue size will be reduced by one
                // now what we are taking an item but blocking queue is empty the it will wait for blocking queue to have an item in it
                // and then it will execute take statement
                val value = blockingQueue.take()
                Log.d(TAG, "consumed value: $value, queue size: ${blockingQueue.size}")
            }
        }
    }
}