package com.example.memo.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object CatatanList : Screen("catatan_list")
    object CatatanForm : Screen("catatan_form?id={id}") {
        fun createRoute(id: Int? = null) = if (id != null) "catatan_form?id=$id" else "catatan_form"
    }
    object CatatanDetail : Screen("catatan_detail/{id}") {
        fun createRoute(id: Int) = "catatan_detail/$id"
    }
    object Arsip : Screen("arsip")
    object Sampah : Screen("sampah")
    object Label : Screen("label")
    object Pengingat : Screen("pengingat")
    object Keamanan : Screen("keamanan")
    object Profil : Screen("profil")
}