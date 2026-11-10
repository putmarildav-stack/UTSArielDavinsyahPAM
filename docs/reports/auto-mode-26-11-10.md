# Laporan Auto Mode: Verifikasi di Ponsel dan 8 Screenshot (UTS PAM)

Tanggal: 10 November 2026 · Lanjutan dari `auto-mode-26-11-09.md`

## Apa yang dikerjakan

Sesi sebelumnya berhenti karena emulator tidak bisa menyala (disk penuh) dan belum ada ponsel tersambung. Hari ini ponsel fisik tersambung, jadi verifikasi visual yang tertunda bisa diselesaikan.

1. **8 screenshot README** diambil dari ponsel TECNO LG7n (Android 12) lewat `adb shell screencap`, disimpan di `docs/screenshots/`. Path-nya sudah cocok dengan yang dirujuk README.
2. **Semua 18 skenario E2E dijalankan** (10 happy path, 5 negative, 3 edge case) dengan tap dan ketik nyata di ponsel. Hasil dan bukti ada di `docs/reports/kopi-dan-nyemil/index.html`.
3. **Dua bug ditemukan saat pengujian, lalu diperbaiki:**
   - Rotasi layar di tab Order atau Profile membuat tab yang menyala tidak cocok dengan isi layar (isi kembali ke Home). Penyebabnya, `MainActivity.onCreate` selalu memaksa tab Home walau activity sedang dipulihkan. Fix: inisialisasi hanya saat `savedInstanceState == null`.
   - Label tab aktif di bottom nav tidak terbaca (putih di atas putih). Fix: selector warna label terpisah `res/color/tab_label.xml`.
4. **Dokumen yang tidak cocok dengan repo diperbaiki:** laporan menyebut commit `710ac29` dan `f2fbb9d` yang tidak ada di riwayat git. Diganti dengan hash sebenarnya `82dc6b2` dan `0473378`.

## Perintah verifikasi dan hasilnya

| Perintah | Hasil |
|---|---|
| `adb devices` | Ponsel `08990372CD000663` terotorisasi |
| `gradlew.bat assembleDebug` (setelah fix) | BUILD SUCCESSFUL in 1m 49s, exit 0 |
| `adb install -r app-debug.apk` | Success |
| 18 skenario di ponsel, dicek dengan `uiautomator dump` dan `dumpsys activity` | 18 lulus. E2 gagal sebelum fix, lulus setelah fix |
| Audit keamanan agent-security | Bersih, tanpa temuan Critical/High/Medium/Low. 3 catatan Info, dua sudah ditangani, satu dicatat di bawah |

## Yang gagal atau dilewati

1. **E2 (rotasi) gagal pada build lama.** Sudah diperbaiki dan diuji ulang, lihat katalog regresi di laporan E2E.
2. **Bukti skenario 9, 10, N4, dan E1 diambil dengan build lama** (sebelum fix label). Perilaku yang diuji tidak terpengaruh fix itu, jadi tidak diambil ulang. 8 screenshot README sudah diambil ulang dengan build baru.
3. **Belum diuji:** versi Android lain, tablet, dan mode gelap sistem. Hanya ada satu ponsel Android 12.
4. **Pesan error yang tidak hilang saat field diisi** dicatat sebagai catatan UX, tidak diubah karena konsisten di semua layar dan tidak menghalangi alur.

## Kelemahan fondasi

1. **Laporan berisi hash commit yang tidak ada.** Riwayat git sempat ditulis ulang setelah laporan dibuat. Sudah dikoreksi di kedua laporan.
2. **Catatan dari audit (Info):** `auto-mode-26-11-09.md` baris 7 memuat path lokal `C:\UTS PAM Ariel\ArielPAM.jpg`. Tidak berisiko, tapi tidak berguna bagi pembaca repo. Dibiarkan karena sudah ada di riwayat git. NIM memang sengaja ditampilkan sebagai identitas tugas (juga ada di README).

## Commit dan perubahan

Branch `docs/screenshots-8-layar`, digabung ke `main` lewat Pull Request:

- `fix:` sinkronkan tab dan isi layar setelah rotasi, perjelas label tab aktif
- `docs:` 8 screenshot README, laporan E2E dengan bukti ponsel, koreksi hash commit
