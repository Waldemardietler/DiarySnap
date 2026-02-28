package com.example.diarysnap.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        topBar = {
            AppTopBar(
                title = "Profil",
                navIcon = Icons.Default.ArrowBack,
                onNavClick = onBack
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {

            Text("Eingeloggt als:")
            Spacer(Modifier.height(6.dp))
            Text(vm.email())

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                ListItem(
                    headlineContent = { Text("Dark Mode") },
                    supportingContent = { Text("Design umschalten") },
                    trailingContent = {
                        Switch(
                            checked = darkMode,
                            onCheckedChange = onDarkModeChange
                        )
                    }
                )
            }

            Spacer(Modifier.height(16.dp))

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