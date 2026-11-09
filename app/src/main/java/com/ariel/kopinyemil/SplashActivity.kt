package com.ariel.kopinyemil

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.ariel.kopinyemil.databinding.ActivitySplashBinding

/**
 * Layar 1: Splash / Welcome.
 * Logo, nama aplikasi, tagline, dan tombol "Start now".
 * Otomatis pindah ke layar Sign up setelah 3 detik.
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())
    private val autoNext = Runnable { bukaSignup() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener { bukaSignup() }
        handler.postDelayed(autoNext, 3000)
    }

    private fun bukaSignup() {
        if (isFinishing) return
        handler.removeCallbacks(autoNext)
        startActivity(Intent(this, SignupActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(autoNext)
    }
}
