# Laporan Auto Mode: Aplikasi Kopi dan Nyemil (UTS PAM)

Tanggal: 9 November 2026 · Sesi: background job (auto mode)

## Apa yang dikerjakan

Tugas UTS Pengembangan Aplikasi Mobile: aplikasi mobile "Kopi dan Nyemil" berdasarkan wireframe di `C:\UTS PAM Ariel\ArielPAM.jpg`, untuk M. Ariel Davinsyah Putra (NIM 1247050122, Kelas B), target repo `putmarildav-stack/UTSArielDavinsyahPAM`.

Ringkasan keputusan dan kejadian:

1. **Pivot framework.** Implementasi awal disiapkan dengan Flutter (SDK ada di mesin), lalu dihentikan setelah pemilik tugas mengonfirmasi materi kelas adalah **Android native (Kotlin)**. Semua artefak Flutter dibersihkan sebelum commit pertama; tidak ada pekerjaan terbuang (agent baru sempat membaca skill).
2. **Implementasi native Kotlin** dikerjakan sendiri (tidak ada agent spesialis Android di daftar): 12 file Kotlin, 10 layout XML, 20 resource (warna, string, tema, ikon vector, adaptive icon, menu bottom nav), 8 layar sesuai wireframe (Splash, Sign Up, Login, Register, Home + 5 menu dummy, Profile, Alamat Pengiriman, Konfirmasi Pesanan) plus bottom navigation 3 tab.
3. **Repo GitHub:** dibuat publik atas instruksi. Push pertama dilakukan SEBELUM tes build sesuai instruksi eksplisit pemilik tugas. Identitas commit diset lokal ke akun mahasiswa (noreply email putmarildav-stack) karena mesin ini punya dua akun gh; penunjuk akun disimpan di `.github-account.md` yang di-gitignore.
4. **Design brief** (wajib auto mode): `docs/kopi-dan-nyemil/index.html`, render terverifikasi via Playwright (3/3 diagram Mermaid jadi SVG, satu-satunya console error adalah favicon 404 yang tidak relevan).

## Perintah verifikasi yang dijalankan beserta hasilnya

| Perintah | Hasil |
|---|---|
| `flutter create` (tahap awal, dibatalkan) | Sukses, lalu dihapus semua saat pivot ke native |
| `gh repo view putmarildav-stack/UTSArielDavinsyahPAM` | Awalnya gagal (repo private, akun salah) → setelah user login putmarildav-stack: OK, lalu diset publik |
| `gradle wrapper --gradle-version 9.1.0` (dari dist ter-cache) | BUILD SUCCESSFUL |
| `gradlew.bat assembleDebug` (percobaan 1) | **BUILD FAILED**: attribute `itemActiveIndicatorTint` tidak ada |
| `gradlew.bat assembleDebug` (percobaan 2, setelah fix) | **BUILD SUCCESSFUL in 4m 35s**, exit 0 (pipefail), APK 6,3 MB |
| Audit keamanan agent-security (sebelum commit) | Lulus, tanpa temuan tinggi/sedang |
| Render design brief via Playwright | OK, 3/3 Mermaid ter-render |
| `emulator.exe -avd KopiTest -no-window ...` | **FATAL: disk C: tidak cukup** (detail di bawah) |
| `adb devices` (ponsel fisik) | Kosong, tidak ada perangkat |

## Yang gagal atau dilewati, beserta alasannya

1. **Verifikasi visual emulator GAGAL karena ruang disk.** Disk C: 100% penuh (sisa 2,2 GB dari 237 GB). Emulator API 37.1 menolak menyala ("not enough disk space", awalnya menuntut 12.288 MB untuk partisi data 10G). Sudah dicoba mengecilkan partisi ke 1G lalu 512M, tetap ditolak cek ruangnya. Tidak ada drive lain di mesin. **Opsi yang tersisa membutuhkan keputusan pemilik mesin** (bersihkan disk atau sambungkan ponsel fisik), sudah dikonfirmasikan ke user pada akhir sesi.
2. **Pembersihan folder Temp TIDAK dijalankan.** Usulan menghapus isi `AppData\Local\Temp` (3,6 GB) ditolak oleh permission classifier dengan alasan yang benar (sweep folder scratch milik user tanpa arahan eksplisit). Tidak dicoba memutar larangan itu.
3. **Screenshot README belum ada.** README sudah menyiapkan tabel 8 screenshot dengan path di `docs/screenshots/`; file gambarnya menyusul begitu emulator bisa hidup atau ponsel disambungkan. Sampai itu terjadi, tautan gambar di GitHub akan tampil kosong.
4. **Build APK release tidak djalankan.** Hanya debug. Alasan: cukup untuk tugas, dan ruang disk yang tinggal 2,2 GB sebaiknya tidak dipakai untuk artefak tambahan.
5. `flutter test` / unit test Android tidak ditulis. Ketentuan tugas tidak memintanya dan verifikasi visual adalah jalur utama; ini keputusan sadar, bukan kelalaian.

## Kelemahan fondasi yang ditemukan dan penanganannya

1. **Disk C: hampir penuh (100%).** Ini masalah mesin, bukan proyek, dan memblokir emulator. Dilaporkan ke user dengan kandidat ruang yang terukur: Temp 3,6 GB, `.gradle/caches` 2,5 GB, NDK 2,2 GB. Dibiarkan untuk keputusan user.
2. **Exit code menipu dari pipeline bash.** Percobaan build pertama dilaporkan "exit 0" padahal BUILD FAILED, karena exit code berasal dari `tail` di ujung pipeline. Diperbaiki dengan `set -o pipefail` pada percobaan kedua. Pelajaran ini diterapkan untuk semua perintah build berikutnya.
3. **Toolchain Windows dipanggil dari WSL:** `flutter`/`gradle`/`avdmanager` tidak bisa dipanggil langsung (CRLF) dan quoting berspasi ke `cmd.exe` rawan patah; solusinya file `.bat` perintah atau `powershell Start-Process`. Emulator yang dijalankan lewat background job bash ikut mati saat wrapper bash selesai; harus dilepas sebagai proses Windows independen.
4. **Dua akun GitHub di satu mesin** (kemalzaki + putmarildav-stack): sudah ditangani dengan `.github-account.md` per repo (ter-ignore) dan verifikasi `gh auth status` sebelum push.

## Commit dan perubahan

| Commit | Isi |
|---|---|
| `94fc9c7` (bawaan user) | README awal |
| `710ac29` | feat: implementasi lengkap aplikasi + design brief + README (audit keamanan lulus sebelum commit) |
| `f2fbb9d` | fix: ganti `itemActiveIndicatorTint` dengan `itemActiveIndicatorStyle` (bug build), build terverifikasi |

Semua sudah ter-push ke `origin/main` repo publik `putmarildav-stack/UTSArielDavinsyahPAM`. Laporan E2E HTML ada di `docs/reports/kopi-dan-nyemil/index.html` dengan status jujur per skenario.

## Status akhir sesi

- Selesai dan terverifikasi: kode, build APK debug, audit keamanan, design brief, README, dokumentasi.
- Menunggu satu keputusan user: bebaskan ruang disk C: (contoh: bersihkan Temp 3,6 GB) ATAU sambungkan ponsel Android fisik dengan USB debugging, supaya verifikasi visual + 8 screenshot bisa diselesaikan.
