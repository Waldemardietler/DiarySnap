package com.example.diarysnap.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LoadingView() {
    CircularProgressIndicator()
}

@Composable
fun ErrorText(message: String) {
    Text("Fehler: $message")
}

