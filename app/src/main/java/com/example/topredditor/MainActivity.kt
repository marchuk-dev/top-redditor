package com.example.topredditor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.topredditor.ui.TopPostsScreen
import com.example.topredditor.ui.theme.TopRedditorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TopRedditorTheme {
                Surface {
                    TopPostsScreen(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}
