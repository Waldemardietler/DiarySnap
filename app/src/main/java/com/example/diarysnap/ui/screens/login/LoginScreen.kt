package com.example.diarysnap.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diarysnap.R
import com.example.diarysnap.ui.components.PrimaryButton
import com.example.diarysnap.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onLoggedIn: () -> Unit
) {
    val vm: AuthViewModel = viewModel()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val error by vm.error.collectAsState()

    Scaffold(
        containerColor = Color(0xFFE3F2FD)
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE3F2FD))
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = "DiarySnap",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFF1A237E)
                )

                Spacer(Modifier.height(24.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email") }
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Passwort") }
                )

                Spacer(Modifier.height(16.dp))

                if (error != null) {
                    Text(text = "❌ $error", color = Color.Red)
                    Spacer(Modifier.height(8.dp))
                }

                PrimaryButton(
                    text = "Einloggen",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        val ok = vm.login(email, password)
                        if (ok) onLoggedIn()
                    }
                )
            }
        }
    }
}