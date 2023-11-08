package com.tasty.recipesapp

import android.content.Intent
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Data Binding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Now you can access views using the 'binding' object
        val editText = binding.editText  // Replace 'editText' with the actual ID in your XML
        val button = binding.button      // Replace 'button' with the actual ID in your XML
//
        val intent = Intent(this, MainActivity::class.java)

        button.setOnClickListener(View.OnClickListener {
            intent.putExtra("message", editText.text.toString())
            startActivity(intent)
        })
//
//
        Toast.makeText(this, "Splash.onCreate", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onCreate: SplashActivity created.")
    }
}
