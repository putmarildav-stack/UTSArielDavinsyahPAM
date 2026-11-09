package com.ariel.kopinyemil

import androidx.annotation.DrawableRes
import java.util.Locale

/** Satu item menu makanan/minuman yang tampil di Home. */
data class MenuItem(
    val nama: String,
    val deskripsi: String,
    val harga: Int,
    val emoji: String,
    @DrawableRes val tileBackground: Int
)

/** Daftar menu statis (dummy) sesuai wireframe, minimal 5 item. */
object DummyData {

    val menu: List<MenuItem> = listOf(
        MenuItem(
            nama = "Kopi Susu Gula Aren",
            deskripsi = "Espresso, susu, gula aren",
            harga = 22000,
            emoji = "☕",
            tileBackground = R.drawable.bg_tile_kopi
        ),
        MenuItem(
            nama = "Donat Cokelat",
            deskripsi = "Donat empuk, glaze cokelat",
            harga = 12000,
            emoji = "🍩",
            tileBackground = R.drawable.bg_tile_donat
        ),
        MenuItem(
            nama = "Sourdough Loaf",
            deskripsi = "Roti fermentasi alami",
            harga = 35000,
            emoji = "🍞",
            tileBackground = R.drawable.bg_tile_roti
        ),
        MenuItem(
            nama = "Cappuccino",
            deskripsi = "Espresso, foam susu lembut",
            harga = 25000,
            emoji = "🥤",
            tileBackground = R.drawable.bg_tile_cappuccino
        ),
        MenuItem(
            nama = "Matcha Latte",
            deskripsi = "Matcha lembut, susu segar",
            harga = 27000,
            emoji = "🍵",
            tileBackground = R.drawable.bg_tile_matcha
        )
    )

    /** Format harga menjadi teks rupiah, contoh: 22000 menjadi "Rp 22.000". */
    fun rupiah(harga: Int): String =
        String.format(Locale("id", "ID"), "Rp %,d", harga)
}
