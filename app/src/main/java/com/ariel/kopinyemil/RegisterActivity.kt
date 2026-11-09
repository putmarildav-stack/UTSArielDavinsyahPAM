package com.ariel.kopinyemil

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ariel.kopinyemil.databinding.ActivityRegisterBinding

/**
 * Layar 4: Register.
 * Semua field wajib diisi. Nama lengkap disimpan sebagai nama pengguna
 * untuk sapaan "Halo, ..." di seluruh aplikasi.
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            var valid = true

            if (nama.isEmpty()) {
                binding.tilNama.error = getString(R.string.error_nama_wajib)
                valid = false
            } else {
                binding.tilNama.error = null
            }
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
                AppData.namaPengguna = nama
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
