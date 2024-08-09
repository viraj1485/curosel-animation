package com.example.androidlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.androidlibrary.ui.theme.AndroidLibraryTheme
import com.example.carousel_animation.curosel.HorizontalCarousel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidLibraryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    HorizontalCarousel(items = listOf(1, 2, 3, 4)) { page, item ->
                        Text(text = item.toString())
                    }
                }
            }
        }
    }
}
