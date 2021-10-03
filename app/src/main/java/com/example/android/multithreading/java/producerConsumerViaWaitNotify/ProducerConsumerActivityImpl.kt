package com.example.android.multithreading.java.producerConsumerViaWaitNotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.multithreading.R

class ProducerConsumerActivityImpl : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producer_consumer_impl)


        // just to understand wait and notify
        /*val processor = Processor()

        val t1 = Thread{
            processor.producer()
        }

        val t2 = Thread{
            processor.consumer()
        }

        t1.start()
        t2.start()*/



        // implementation of wait and notify for producer and consumer approach
        val processor = ActualProcessor()

        val t1 = Thread{
            processor.producer()
        }

        val t2 = Thread{
            processor.consumer()
        }

        t1.start()
        t2.start()
    }
}