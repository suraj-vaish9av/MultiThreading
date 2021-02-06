package com.example.android.multithreading.threadLooperHandler

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast


class DownloadHandler(val context: Context, myLooper: Looper) : Handler(myLooper) {

    val TAG="DownloadHandler"

    override fun handleMessage(msg: Message) {
       // super.handleMessage(msg)

        val isOnUiThread = Thread.currentThread() === Looper.getMainLooper().thread

        Log.d(TAG, "thread: ${Thread.currentThread().name}, OnUiThread: $isOnUiThread")
        val songName= msg.obj.toString()
        downloadSong(songName)
        Toast.makeText(context,"hello from handler",Toast.LENGTH_SHORT).show()
    }

    private fun downloadSong(songName: String){
        Log.d(TAG, "downloading song")
        Thread.sleep(2000)
        Log.d(TAG, "song downloaded: $songName")
    }
}