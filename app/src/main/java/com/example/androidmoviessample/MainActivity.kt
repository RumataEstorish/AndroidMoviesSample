package com.example.androidmoviessample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.androidmoviessample.ui.navigation.MainNavigationHolder
import com.example.androidmoviessample.ui.theme.AndroidMoviesSampleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidMoviesSampleTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    MainNavigationHolder()
                }
            }
        }
    }
}
