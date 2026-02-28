package com.example.diarysnap.ui.screens.create

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        topBar = {
            AppTopBar(
                title = "Neuer Eintrag",
                navIcon = Icons.Default.ArrowBack,
                onNavClick = onBack
            )
        }
    ) { padding ->

        // Hintergrund bleibt hell/freundlich (kommt vom Theme)
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Haupt-Card (wirkt „fertiger“)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(Modifier.padding(16.dp)) {

                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Titel") },
                        singleLine = true
                    )

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = mood,
                        onValueChange = { mood = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Mood/Keyword (z.B. happy, nature)") },
                        singleLine = true
                    )

                    Spacer(Modifier.height(12.dp))

                    PrimaryButton(
                        text = if (loading) "Lade..." else "Bild laden",
                        enabled = !loading,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { vm.loadImage(mood) }
                    )

                    Spacer(Modifier.height(14.dp))

                    if (!imageUrl.isNullOrBlank()) {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            AsyncImage(
                                model = imageUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(190.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                        }
                        Spacer(Modifier.height(14.dp))
                    }

                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 140.dp),
                        label = { Text("Text") },
                        minLines = 5
                    )

                    Spacer(Modifier.height(12.dp))

                    if (error != null) {
                        Card(
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.errorContainer
                            )
                        ) {
                            Text(
                                text = "❌ $error",
                                modifier = Modifier.padding(12.dp),
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                        Spacer(Modifier.height(12.dp))
                    }

                    PrimaryButton(
                        text = if (loading) "Speichere..." else "Speichern",
                        enabled = !loading,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { vm.save(title, text, mood) { onBack() } }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            // Kleiner Hinweis unten (optional)
            Text(
                text = "Tipp: Nutze Keywords wie „nature“, „happy“, „sunset“.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.padding(horizontal = 6.dp)
            )
        }
    }
}