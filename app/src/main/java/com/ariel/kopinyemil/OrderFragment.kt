package com.ariel.kopinyemil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ariel.kopinyemil.databinding.FragmentOrderBinding

/**
 * Tab Order: layar Alamat Pengiriman (layar 7 pada wireframe).
 * Nama lengkap, alamat, dan patokan wajib diisi sebelum memesan.
 */
class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pra-isi nama lengkap dari data sesi agar tidak perlu mengetik ulang
        if (binding.etNama.text.isNullOrBlank()) {
            binding.etNama.setText(AppData.namaPengguna)
        }

        binding.btnOrder.setOnClickListener { prosesPesanan() }
    }

    private fun prosesPesanan() {
        val nama = binding.etNama.text.toString().trim()
        val alamat = binding.etAlamat.text.toString().trim()
        val patokan = binding.etPatokan.text.toString().trim()
        var valid = true

        if (nama.isEmpty()) {
            binding.tilNama.error = getString(R.string.error_nama_wajib)
            valid = false
        } else {
            binding.tilNama.error = null
        }
        if (alamat.isEmpty()) {
            binding.tilAlamat.error = getString(R.string.error_alamat_wajib)
            valid = false
        } else {
            binding.tilAlamat.error = null
        }
        if (patokan.isEmpty()) {
            binding.tilPatokan.error = getString(R.string.error_patokan_wajib)
            valid = false
        } else {
            binding.tilPatokan.error = null
        }

        if (valid) {
            AppData.orderNama = nama
            AppData.orderAlamat = alamat
            AppData.orderPatokan = patokan
            AppData.kembaliKeTabHome = true
            startActivity(Intent(requireContext(), ConfirmationActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
