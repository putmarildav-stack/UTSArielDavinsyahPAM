package com.ariel.kopinyemil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ariel.kopinyemil.databinding.ActivityConfirmationBinding

/**
 * Layar 8: Konfirmasi Pesanan.
 * Menampilkan ucapan terima kasih dan ringkasan alamat pengiriman.
 * Tombol "Oke" menutup layar ini dan MainActivity kembali ke tab Home.
 */
class ConfirmationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvGreeting.text = getString(R.string.greeting_halo, AppData.namaPengguna)
        binding.tvSummaryNama.text = AppData.orderNama
        binding.tvSummaryAlamat.text = AppData.orderAlamat
        binding.tvSummaryPatokan.text = AppData.orderPatokan

        binding.btnOke.setOnClickListener {
            finish()
        }
    }
}
