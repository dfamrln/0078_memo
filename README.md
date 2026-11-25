# Memo — Aplikasi Catatan Pribadi dengan Kunci PIN dan Biometrik

## Perkenalan Aplikasi

**Memo** adalah aplikasi catatan pribadi berbasis Android yang dibangun menggunakan **Kotlin**, **Jetpack Compose (Material Design 3)**, **Navigation Compose**, dan basis data **PostgreSQL**. Aplikasi ini dirancang untuk membantu pengguna mencatat teks maupun daftar tugas (*checklist*), mengelompokkan catatan dengan label, mengatur pengingat waktu, serta mengamankan catatan pribadi menggunakan kunci **PIN dan biometrik**.

---

## Progress Mingguan (Weekly Progress)

### Minggu 1 – 2 (12 November 2026 – 25 November 2026) *(Gabungan Minggu 1 & 2)*
* **Inisiasi Proyek & Navigasi:** Setup repositori `0078_Memo`, konfigurasi `.gitignore`, inisialisasi proyek Android Jetpack Compose, serta pembuatan rute navigasi aplikasi (`Screen.kt`).
* **Perancangan Basis Data PostgreSQL:** Penyusunan skema 7 tabel PostgreSQL (`users`, `catatan`, `label`, `catatan_label`, `checklist_item`, `pengingat`, `refresh_tokens`) pada `database/schema.sql` untuk peran User.
* **Antarmuka Autentikasi (`LoginScreen` & `RegisterScreen`):** Pembuatan halaman masuk dan pendaftaran akun lengkap dengan validasi input berbahasa Indonesia sesuai spesifikasi SRS.
* **Antarmuka Beranda Catatan (`CatatanListScreen`):** Pembuatan tampilan daftar catatan utama dengan fitur pencarian, penanda catatan tersemat (*pin*), dan indikator catatan terkunci.