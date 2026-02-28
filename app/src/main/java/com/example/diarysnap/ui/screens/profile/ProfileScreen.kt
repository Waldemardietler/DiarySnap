package com.example.diarysnap.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diarysnap.ui.components.AppTopBar
import com.example.diarysnap.ui.components.PrimaryButton
import com.example.diarysnap.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    onLogout: () -> Unit,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit
) {
    val vm: ProfileViewModel = viewModel()

    Scaffold(
        containerColor = Color(0xFFE3F2FD),
        topBar = {
            AppTopBar(
                title = "Settings",
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
            Text("Eingeloggt als:", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(6.dp))
            Text(vm.email(), style = MaterialTheme.typography.bodyLarge)

            Spacer(Modifier.height(18.dp))

            Card {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Dark Mode", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "Dunkles Design aktivieren",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        )
                    }
                    Switch(
                        checked = darkMode,
                        onCheckedChange = { onDarkModeChange(it) }
                    )
                }
            }

            Spacer(Modifier.height(18.dp))

            PrimaryButton(
                text = "Logout",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    vm.logout()
                    onLogout()
                }
            )
        }
    }
}