package com.example.diarysnap.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.compose.AsyncImage
import com.example.diarysnap.AppServices
import com.example.diarysnap.ui.components.AppTopBar
import com.example.diarysnap.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onCreate: () -> Unit,
    onOpenDetail: (Long) -> Unit,
    onOpenProfile: () -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current

    val vm: HomeViewModel = viewModel(
        factory = viewModelFactory {
            initializer { HomeViewModel(AppServices.diaryRepository(context)) }
        }
    )

    val entries by vm.entries.collectAsState(initial = emptyList())

    Scaffold(
        containerColor = Color(0xFFE3F2FD),
        topBar = {
            AppTopBar(
                title = "DiarySnap",
                actionIcon = Icons.Default.AccountCircle,
                onActionClick = onOpenProfile
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreate,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (entries.isEmpty()) {
                Card(
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Noch keine Einträge", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "Tippe auf + und erstelle deinen ersten Tagebucheintrag.",
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                        )
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 88.dp)
                ) {
                    items(entries) { e ->
                        Card(
                            shape = RoundedCornerShape(18.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onOpenDetail(e.id) }
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                if (!e.imageUrl.isNullOrBlank()) {
                                    AsyncImage(
                                        model = e.imageUrl,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(64.dp)
                                            .clip(RoundedCornerShape(14.dp))
                                    )
                                } else {
                                    Surface(
                                        modifier = Modifier.size(64.dp),
                                        shape = RoundedCornerShape(14.dp),
                                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                                    ) {}
                                }

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(e.title, style = MaterialTheme.typography.titleMedium, maxLines = 1)
                                    Spacer(Modifier.height(8.dp))
                                    AssistChip(
                                        onClick = {},
                                        label = { Text(e.mood.ifBlank { "ohne Mood" }) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}