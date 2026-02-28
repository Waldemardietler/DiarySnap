package com.example.diarysnap.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.AsyncImage
import com.example.diarysnap.AppServices
import com.example.diarysnap.ui.components.AppTopBar
import com.example.diarysnap.ui.components.PrimaryButton
import com.example.diarysnap.viewmodel.DetailViewModel

@Composable
fun DetailScreen(
    entryId: Long,
    onBack: () -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current

    val vm: DetailViewModel = viewModel(
        factory = viewModelFactory {
            initializer { DetailViewModel(AppServices.diaryRepository(context)) }
        }
    )

    val entry by vm.entry(entryId).collectAsState(initial = null)

    Scaffold(
        containerColor = Color(0xFFE3F2FD),
        topBar = {
            AppTopBar(
                title = "Detail",
                navIcon = Icons.Default.ArrowBack,
                onNavClick = onBack
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (entry == null) {
                Text("Eintrag nicht gefunden.")
                return@Column
            }

            if (!entry!!.imageUrl.isNullOrBlank()) {
                AsyncImage(
                    model = entry!!.imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
                Spacer(Modifier.height(12.dp))
            }

            Text(entry!!.title, style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(6.dp))
            Text(entry!!.mood.ifBlank { "ohne Mood" })
            Spacer(Modifier.height(12.dp))
            Text(entry!!.text)

            Spacer(Modifier.height(16.dp))

            PrimaryButton(
                text = "Löschen",
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.delete(entryId) { onBack() } }
            )
        }
    }
}