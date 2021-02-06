package com.example.android.multithreading

import android.os.*
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.multithreading.threadLooperHandler.DownloadThread


class MainActivity : AppCompatActivity() {

    lateinit var txtData:TextView

    val TAG="MainActivity"

    lateinit var progress:ProgressBar
    lateinit var handler : Handler

    val myDownloadThread by lazy { DownloadThread(this@MainActivity) }

    companion object{
        val MESSAGE_KEY="MESSAGE_KEY"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isOnUiThread = Thread.currentThread() === Looper.getMainLooper().thread

        Log.d(TAG, "thread: ${Thread.currentThread().name}, OnUiThread: $isOnUiThread")


        progress=findViewById(R.id.progress)

        txtData=findViewById(R.id.txtData)
        txtData.movementMethod = ScrollingMovementMethod()

        handler= Handler(mainLooper){
            val message = it.data.getString(MESSAGE_KEY)
            Log.d(TAG, "message: $message")
            progress.visibility=View.GONE
            return@Handler true
        }

        myDownloadThread.start()



        val myHandlerThread= MyHandlerThread(this@MainActivity)
        myHandlerThread.start()
        /*myHandlerThread.postRunnable {
            Toast.makeText(this@MainActivity,"hello MyHandlerThread from runnable", Toast.LENGTH_SHORT).show()
        }*/

    }

    fun runCode(view: View) {
        addMessage()

        // 1. pauses the execution of main thread
        //Thread.sleep(4000)

        // 2. post add a runnable to message queue
        // Even putting Thread.sleep in the post method of handler, it will still run on main thread and block the main thread same as no. 1
        /*Handler().post {
            Thread.sleep(4000) // this will run on main thread
        }*/

        // 3. In postDelayed add the runnable to message queue and run it after the given time elapses
        Handler().postDelayed(Runnable {
            Log.d(TAG, "running from handler 1") // this will still run on main thread
        }, 4000)

        Handler().postDelayed(Runnable {
            Log.d(TAG, "running from handler 2") // this will still run on main thread
        }, 2000)

    }

    fun startThread(view: View){
        val runnable = Runnable {
            Log.d(TAG, "starting download")
            Log.d(TAG, "thread name: ${Thread.currentThread().name}")
            Thread.sleep(4000)
            Log.d(TAG, "download complete")

            // sending data from bg thread to
            val message = Message()
            val bundle = Bundle()
            bundle.putString(MESSAGE_KEY, "Download Completed")
            message.data = bundle
            handler.sendMessage(message)
        }

        // create a thread and pass the runnable
        val thread = Thread(runnable)
        thread.name="Download Thread"   // if the name doesn't given it would be Thread1
        // thread.run() don't call run method because that would call run method on main thread

        progress.visibility=View.VISIBLE

        thread.start() // this will also call the run method but on the bg thread
    }

    fun startThread2(view: View){
        val myThread= MyDownloadThread()
        myThread.start()
    }

    fun startThread3(view: View){
        progress.visibility=View.VISIBLE
        playList.forEach { songName->
            myDownloadThread.sendMessage(songName)
        }
    }


    private fun addMessage(){
        txtData.append("\n Running code")
        Log.d(TAG, "message added to textview")
    }


}