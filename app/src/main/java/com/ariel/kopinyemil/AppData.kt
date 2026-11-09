package com.ariel.kopinyemil

/**
 * Penyimpanan data sesi di memori (dummy).
 * Aplikasi ini tidak memakai database sesuai ketentuan tugas,
 * jadi data hanya hidup selama aplikasi berjalan.
 */
object AppData {

    /** Nama yang dipakai untuk sapaan "Halo, ..." di seluruh aplikasi. */
    var namaPengguna: String = "Sobat Kopi"

    /** Data alamat pengiriman terakhir yang diketik pengguna di tab Order. */
    var orderNama: String = ""
    var orderAlamat: String = ""
    var orderPatokan: String = ""

    /**
     * Penanda bahwa pesanan baru saja dibuat, supaya MainActivity
     * memindahkan tab aktif kembali ke Home saat pengguna menekan "Oke"
     * atau tombol back di layar konfirmasi.
     */
    var kembaliKeTabHome: Boolean = false
}
