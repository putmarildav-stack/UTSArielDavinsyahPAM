package com.ariel.kopinyemil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ariel.kopinyemil.databinding.ItemMenuBinding

/**
 * Adapter RecyclerView untuk daftar menu di Home.
 * Pakai ViewBinding per item seperti pola standar kuliah.
 */
class MenuAdapter(private val daftarMenu: List<MenuItem>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = ItemMenuBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = daftarMenu[position]
        with(holder.binding) {
            tvName.text = item.nama
            tvDesc.text = "• ${item.deskripsi}"
            tvPrice.text = DummyData.rupiah(item.harga)
            tvEmoji.text = item.emoji
            tvEmoji.setBackgroundResource(item.tileBackground)
        }
    }

    override fun getItemCount(): Int = daftarMenu.size
}
