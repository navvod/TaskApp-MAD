package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.App
import com.example.notesapp.R

// SplashActivity.kt
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, App::class.java))
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }

    companion object {
        private const val SPLASH_DISPLAY_LENGTH = 3000L // 3 seconds
    }
}
