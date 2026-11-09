package com.ariel.kopinyemil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ariel.kopinyemil.databinding.ActivityLoginBinding

/**
 * Layar 3: Login User.
 * Validasi sederhana: username dan password wajib diisi.
 * Tidak ada database, jadi kombinasi apa pun yang terisi diterima.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            var valid = true

            if (username.isEmpty()) {
                binding.tilUsername.error = getString(R.string.error_username_wajib)
                valid = false
            } else {
                binding.tilUsername.error = null
            }
            if (password.isEmpty()) {
                binding.tilPassword.error = getString(R.string.error_password_wajib)
                valid = false
            } else {
                binding.tilPassword.error = null
            }

            if (valid) {
                AppData.namaPengguna = username
                bukaMainActivity()
            }
        }
    }

    private fun bukaMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }
}
