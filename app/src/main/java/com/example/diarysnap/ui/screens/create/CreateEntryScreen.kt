package com.example.diarysnap.ui.screens.create

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.example.diarysnap.viewmodel.CreateEntryViewModel

@Composable
fun CreateEntryScreen(onBack: () -> Unit) {
    val context = androidx.compose.ui.platform.LocalContext.current

    val vm: CreateEntryViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                CreateEntryViewModel(
                    repo = AppServices.diaryRepository(context),
                    api = AppServices.unsplashApi
                )
            }
        }
    )

    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var mood by remember { mutableStateOf("") }

    val loading by vm.loading.collectAsState()
    val imageUrl by vm.imageUrl.collectAsState()
    val error by vm.error.collectAsState()

    Scaffold(
        containerColor = Color(0xFFE3F2FD),
        topBar = {
            AppTopBar(
                title = "Neuer Eintrag",
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
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Titel") }
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = mood,
                onValueChange = { mood = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Mood/Keyword (z.B. happy)") }
            )

            Spacer(Modifier.height(8.dp))

            PrimaryButton(
                text = if (loading) "Lade..." else "Bild laden",
                enabled = !loading,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.loadImage(mood) }
            )

            Spacer(Modifier.height(12.dp))

            if (!imageUrl.isNullOrBlank()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                Spacer(Modifier.height(12.dp))
            }

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Text") },
                minLines = 5
            )

            Spacer(Modifier.height(12.dp))

            if (error != null) Text("❌ $error")

            Spacer(Modifier.height(12.dp))

            PrimaryButton(
                text = if (loading) "Speichere..." else "Speichern",
                enabled = !loading,
                modifier = Modifier.fillMaxWidth(),
                onClick = { vm.save(title, text, mood) { onBack() } }
            )
        }
    }
}