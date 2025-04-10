package com.example.wakeupfriend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wakeupfriend.ui.theme.WakeUpFriendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Compose UI content here
            WakeUpFriendTheme {
                // Call your composables here
            }
        }
    }
}
