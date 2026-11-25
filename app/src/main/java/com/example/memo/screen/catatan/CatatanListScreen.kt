package com.example.memo.screen.catatan

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

data class DummyCatatan(
    val id: Int,
    val judul: String,
    val isi: String,
    val isPinned: Boolean,
    val isLocked: Boolean,
    val label: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatatanListScreen(
    onTambahCatatan: () -> Unit,
    onLogout: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val sampleCatatan = remember {
        listOf(
            DummyCatatan(1, "Jadwal Kuliah PAM", "Presentasi Progress Mingguan & Demo UI Compose", true, false, "Kuliah"),
            DummyCatatan(2, "PIN & Password Penting", "Isi disembunyikan karena terkunci", false, true, "Pribadi"),
            DummyCatatan(3, "Daftar Belanja Bulanan", "Beras, kopi, gula, sabun mandi", false, false, "Belanja")
        )
    }

    val filteredList = sampleCatatan.filter {
        if (it.isLocked) {
            it.judul.contains(searchQuery, ignoreCase = true)
        } else {
            it.judul.contains(searchQuery, ignoreCase = true) ||
                    it.isi.contains(searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Memo Saya", fontWeight = FontWeight.Bold) },
                actions = {
                    TextButton(onClick = onLogout) {
                        Text("Keluar")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onTambahCatatan) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Catatan")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Cari catatan...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Cari") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(filteredList) { catatan ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = catatan.judul,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.weight(1f)
                                )
                                if (catatan.isPinned) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Disematkan",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                if (catatan.isLocked) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Terkunci",
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = if (catatan.isLocked) "Catatan terkunci (verifikasi PIN/biometrik)" else catatan.isi,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            AssistChip(
                                onClick = {},
                                label = { Text(catatan.label, style = MaterialTheme.typography.labelSmall) }
                            )
                        }
                    }
                }
            }
        }
    }
}