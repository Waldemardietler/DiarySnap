package com.example.diarysnap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.diarysnap.ui.navigation.AppNavGraph
import com.example.diarysnap.ui.theme.DiarySnapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiarySnapTheme {
                Surface {
                    AppNavGraph()
                }
            }
        }
    }
}