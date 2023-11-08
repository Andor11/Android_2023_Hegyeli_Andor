package com.tasty.recipesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")

        Toast.makeText(this, "Main.onCreate", Toast.LENGTH_LONG).show()
//
        val message = intent.getStringExtra("message")

        // Display the data in a TextView (assuming you have a TextView with id 'textView' in activity_main.xml)
        val textView = findViewById<TextView>(R.id.textView)
        textView.text = message
//
        val startSplashButton = findViewById<Button>(R.id.startSplashButton)
        startSplashButton.setOnClickListener {
            // Create an Intent to start SplashActivity
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }
    }
}