package com.tasty.recipesapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tasty.recipesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SplashActivity"
    }

    private lateinit var binding: ActivitySplashBinding
    private val SPLASH_TIME_OUT = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Data Binding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Toast.makeText(this, "Splash.onCreate", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onCreate: SplashActivity created.")

        val handlerThread = HandlerThread("SplashHandlerThread", -10)
        handlerThread.start() // Create a Handler on the new HandlerThread
        val handler = Handler(handlerThread.looper)
        handler.postDelayed({
// Navigate to MainActivity after the delay
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish() },
            SPLASH_TIME_OUT.toLong()
        )
    }
}
