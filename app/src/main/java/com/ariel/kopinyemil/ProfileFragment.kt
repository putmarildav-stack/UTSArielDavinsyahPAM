package com.ariel.kopinyemil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ariel.kopinyemil.databinding.FragmentProfileBinding

/**
 * Tab Profile (layar 6 pada wireframe).
 * Pengguna bisa mengubah nama yang dipakai sapaan aplikasi.
 */
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etNama.setText(AppData.namaPengguna)
        binding.btnSave.setOnClickListener { simpanProfil() }
        segarkanSapaan()
    }

    private fun simpanProfil() {
        val nama = binding.etNama.text.toString().trim()
        if (nama.isEmpty()) {
            binding.tilNama.error = getString(R.string.error_nama_wajib)
            return
        }
        binding.tilNama.error = null
        AppData.namaPengguna = nama
        segarkanSapaan()
        Toast.makeText(requireContext(), R.string.profil_tersimpan, Toast.LENGTH_SHORT).show()
    }

    fun segarkanSapaan() {
        _binding?.tvGreeting?.text =
            getString(R.string.greeting_halo, AppData.namaPengguna)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
