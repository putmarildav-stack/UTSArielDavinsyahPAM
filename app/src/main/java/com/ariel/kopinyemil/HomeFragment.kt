package com.ariel.kopinyemil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ariel.kopinyemil.databinding.FragmentHomeBinding

/**
 * Tab Home: sapaan pengguna dan daftar 5 menu (data dummy).
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerMenu.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerMenu.adapter = MenuAdapter(DummyData.menu)
        segarkanSapaan()
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
