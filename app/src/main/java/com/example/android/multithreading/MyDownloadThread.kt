package com.example.android.multithreading

import android.util.Log

val playList = arrayOf("Hotel California", "Private Emotion","Riders on the storm")

class MyDownloadThread : Thread() {

    val TAG="MyDownloadThread"

    override fun run() {
        super.run()

        playList.forEach {songName->
            downloadSong(songName)
        }
    }

    private fun downloadSong(songName:String){
        Log.d(TAG,"downloading song")
        Thread.sleep(2000)
        Log.d(TAG,"song downloaded: $songName")
    }
}