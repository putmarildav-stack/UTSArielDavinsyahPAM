package com.ariel.kopinyemil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ariel.kopinyemil.databinding.ActivityMainBinding

/**
 * Layar 5-7: wadah utama dengan bottom navigation.
 * Tab Home, Order (Alamat Pengiriman), dan Profile dibawakan Fragment.
 * Fragment dipakai ulang (show/hide) supaya isi form tidak hilang
 * saat berpindah tab.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> tampilkanFragment(TAG_HOME)
                R.id.nav_order -> tampilkanFragment(TAG_ORDER)
                R.id.nav_profile -> tampilkanFragment(TAG_PROFILE)
            }
            true
        }

        // Hanya saat pertama dibuka. Saat activity dibuat ulang (misalnya layar
        // diputar), FragmentManager dan bottom nav memulihkan tab aktif sendiri;
        // memaksa ke Home di sini membuat isi layar dan tab yang menyala tidak cocok.
        if (savedInstanceState == null) {
            // Buat ketiga fragment sekali di awal supaya bisa saling disembunyikan
            tampilkanFragment(TAG_HOME)
            tampilkanFragment(TAG_ORDER)
            tampilkanFragment(TAG_PROFILE)
            binding.bottomNav.selectedItemId = R.id.nav_home
        }
    }

    override fun onResume() {
        super.onResume()
        // Setelah pesanan dikonfirmasi, kembali ke tab Home
        if (AppData.kembaliKeTabHome) {
            AppData.kembaliKeTabHome = false
            binding.bottomNav.selectedItemId = R.id.nav_home
        }
        segarkanSapaan()
    }

    private fun tampilkanFragment(tag: String) {
        val fm = supportFragmentManager
        val tx = fm.beginTransaction()
        for (fragment in fm.fragments) {
            tx.hide(fragment)
        }
        val ada = fm.findFragmentByTag(tag)
        if (ada != null) {
            tx.show(ada)
        } else {
            val baru: Fragment = when (tag) {
                TAG_ORDER -> OrderFragment()
                TAG_PROFILE -> ProfileFragment()
                else -> HomeFragment()
            }
            tx.add(R.id.fragmentContainer, baru, tag)
        }
        tx.commit()
        segarkanSapaan()
    }

    /** Perbarui sapaan "Halo, ..." agar selalu memakai nama terbaru. */
    private fun segarkanSapaan() {
        val home = supportFragmentManager.findFragmentByTag(TAG_HOME) as? HomeFragment
        val profile = supportFragmentManager.findFragmentByTag(TAG_PROFILE) as? ProfileFragment
        home?.segarkanSapaan()
        profile?.segarkanSapaan()
    }

    companion object {
        private const val TAG_HOME = "home"
        private const val TAG_ORDER = "order"
        private const val TAG_PROFILE = "profile"
    }
}
