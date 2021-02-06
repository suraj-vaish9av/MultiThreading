package com.example.android.multithreading.threadLooperHandler

import android.content.Context
import android.os.Looper
import android.os.Message
import android.widget.Toast

class DownloadThread(val context: Context) : Thread() {

    lateinit var downloadHandler : DownloadHandler

    override fun run() {
        super.run()



        Looper.prepare()

        Toast.makeText(context,"hello from thread", Toast.LENGTH_SHORT).show()

        val looper = Looper.myLooper()
        while (isAlive && looper == null) {
            try {

            } catch (e: InterruptedException) {
            }
        }

        downloadHandler=DownloadHandler(context,looper!!)
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