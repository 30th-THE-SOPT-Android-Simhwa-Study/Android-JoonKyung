package com.lee989898.myapplication

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lee989898.myapplication.databinding.ActivityMainBinding
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myHandler: Handler
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myHandler = MyHandler()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btn1.setOnClickListener {
            BackgroundThread1().start()
        }
        binding.btn2.setOnClickListener {
            BackgroundThread2().start()
        }
        binding.img.setOnClickListener {
            BackgroundThread3().start()
        }
    }

    @SuppressLint("HandlerLeak")
    inner class MyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            // 다른 Thread에서 전달받은 Message 처리
            val getBundle = msg.data
            val img = getBundle.getString("image")
            img.let {
                binding.img.post {
                    binding.img.setImageBitmap(ConvertBitMap().StringToBitmap(it))
                }
            }
            binding.count.text = getBundle.getInt("count").toString()
        }
    }

    inner class BackgroundThread1 : Thread() {
        override fun run() {
            //implement Image 1
            val minho = getBitmapFromURL("https://avatars.githubusercontent.com/u/15981307?v=4")
            val bundle = Bundle()
            bundle.putString("image", minho?.let { ConvertBitMap().BitmapToString(minho) })
            val msg = myHandler.obtainMessage()
            with(msg) {
                data = bundle
                SystemClock.sleep(2000)
                myHandler.sendMessage(this)
            }
        }
    }
    inner class BackgroundThread2 : Thread() {
        override fun run() {
            //implement Image 2
            val joonKyung = getBitmapFromURL("https://avatars.githubusercontent.com/u/15981307?v=4")
            val bundle = Bundle()
            bundle.putString("image", joonKyung?.let { ConvertBitMap().BitmapToString(joonKyung) })
            val msg = myHandler.obtainMessage()
            with(msg) {
                data = bundle
                SystemClock.sleep(2000)
                myHandler.sendMessage(this)
            }
        }
    }
    inner class BackgroundThread3 : Thread() {
        override fun run() {
            //implement Count
            while (isAlive) {
                val bundle = Bundle()
                val msg = myHandler.obtainMessage()
                count++
                bundle.putInt("count", count)
                with(msg) {
                    data = bundle
                    SystemClock.sleep(1000)
                    myHandler.sendMessage(this)
                }
            }
        }
    }

    private fun getBitmapFromURL(src: String): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput= true  //읽기모드임
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}