package com.demo_by_abhinav.currencyexchangeconverterapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.demo_by_abhinav.currencyexchangeconverterapp.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gotoMainActivity()
    }

    private fun gotoMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            MainActivity.getStartIntent(this).apply {
                startActivity(this)
                finish()
            }
            finish()
        },
            SPLASH_WAITING_TIME
        )
    }
    companion object {
        private const val SPLASH_WAITING_TIME = 500L // 2 seconds.
    }
}