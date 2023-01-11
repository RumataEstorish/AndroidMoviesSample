package com.example.androidmoviessample.ui.error

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidmoviessample.ui.theme.AndroidMoviesSampleTheme
import com.example.androidmoviessample.ui.theme.Typography

@Composable
fun ErrorScreenContent(message: String, actionButtonText: String, onActionClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = message,
            fontSize = Typography.titleLarge.fontSize,
            color = MaterialTheme.colorScheme.onSurface
        )
        Button(
            onClick = onActionClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = actionButtonText)
        }
    }
}

@Preview
@Composable
fun PreviewErrorScreen() {
    AndroidMoviesSampleTheme {
        ErrorScreenContent(message = "Test error", "Refresh") {
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewErrorScreenNight() {
    AndroidMoviesSampleTheme {
        ErrorScreenContent(message = "Test error", "Refresh") {
        }
    }
}