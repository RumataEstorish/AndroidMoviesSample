package com.example.androidmoviessample.ui.utils

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TopAppBarTitle(title: String) {
    Text(
        text = title,
        fontSize = MaterialTheme.typography.titleSmall.fontSize
    )
}