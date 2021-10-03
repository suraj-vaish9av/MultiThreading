package com.example.android.multithreading.threadLooperHandler

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast


class DownloadHandler(val context: Context, myLooper: Looper) : Handler(myLooper) {

    val TAG="DownloadHandler"
    var counter=0

    override fun handleMessage(msg: Message) {
       // super.handleMessage(msg)

        val isOnUiThread = Thread.currentThread() === Looper.getMainLooper().thread


        Log.d(TAG, "thread: ${Thread.currentThread().name}, OnUiThread: $isOnUiThread")
        val songName= msg.obj.toString()
        downloadSong(songName)

        counter++
        Toast.makeText(context,"hello from handler count: $counter, isOnUiThread: $isOnUiThread",Toast.LENGTH_SHORT).show()

    }

    private fun downloadSong(songName: String){
        Log.d(TAG, "downloading song")
        Thread.sleep(200)
        Log.d(TAG, "song downloaded: $songName")
    }
}