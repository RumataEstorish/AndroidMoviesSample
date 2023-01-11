package com.example.androidmoviessample.ui.utils

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.androidmoviessample.R

@Composable
fun ImageLoading(modifier: Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.image_placeholder),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun ImageError(modifier: Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.image_placeholder),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun ImageEmpty(modifier: Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.image_placeholder),
        contentDescription = null,
        modifier = modifier
    )
}