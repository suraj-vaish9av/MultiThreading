package com.example.android.multithreading.threadLooperHandler

import android.content.Context
import android.os.Looper
import android.os.Message
import android.widget.Toast

/**
 * Use Case 2. Generally when a thread starts, complete its work and die, if we want to do some more work on bg thread we need to create a new thread.
 * creation of thread is an expensive task, to overcome this issue, we use thread with handler,
 * here we have created a thread, inside run method,
 * Looper.prepare() creates a looper for this thread and a message queue
 * after that we have created a handler, note that if we do not pass looper to Handler's constructor, it will run on the same thread which instantiated it,
 * if we pass looper to the handler's constructor it means, it will run on the thread of the looper (means runs on the thread looper belongs to)
 * looper.loop() to start the looper
 */
class DownloadThread(val context: Context) : Thread() {

    lateinit var downloadHandler : DownloadHandler

    override fun run() {
        super.run()



        Looper.prepare()

        // the very strange thing is here, even if it is a bg thread
        // , the following toast will be shown without any issue because we have called Looper.prepare()
        // what exactly makes a thread, a main thread (Looper.prepare()?) ?
        Toast.makeText(context,"hello from thread after Looper.prepare", Toast.LENGTH_SHORT).show()

        val looper = Looper.myLooper()
        while (isAlive && looper == null) {
            try {

            } catch (e: InterruptedException) {
            }
        }


        downloadHandler=DownloadHandler(context,looper!!)   // pass Looper.getMainLooper() for main thread
        // , we have passed looper reference which belongs to this thread, as it is bg thread so the handler work on bg thread
        Looper.loop()

        //Toast.makeText(context,"hello from thread 1", Toast.LENGTH_SHORT).show()
    }

    fun sendMessage(songName:String)
    {
        val message = Message.obtain()
        message.obj=songName
        downloadHandler.sendMessage(message)
    }
}